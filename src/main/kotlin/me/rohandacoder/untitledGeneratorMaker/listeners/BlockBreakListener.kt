package me.rohandacoder.untitledGeneratorMaker.listeners

import com.jeff_media.customblockdata.CustomBlockData
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title.Times.times
import net.kyori.adventure.title.Title.title
import net.kyori.adventure.util.Ticks.duration
import org.bukkit.NamespacedKey
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class BlockBreakListener(private val plugin: UntitledGeneratorMaker) : Listener {
    val generatorKey = NamespacedKey(plugin, "shouldRegenerate")

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onBreak(event: BlockBreakEvent) {
        val player = event.player
        val customBlockData = CustomBlockData(event.block, plugin)
        val value = customBlockData.get(generatorKey, PersistentDataType.BOOLEAN) ?: false
        if (!value) return

        event.isDropItems = false

        val drops = getBlockDrops(event.block)

        // If inventory is full
        if (drops.any { player.inventory.addItem(it).isNotEmpty() }) {
            player.playSound(player.location, Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f)
            player.showTitle(
                title(
                    Component.text("Inventory Full!").color(NamedTextColor.DARK_RED)
                        .decoration(TextDecoration.BOLD, true),
                    Component.text("Make space to collect items").color(NamedTextColor.RED),
                    times(duration(1), duration(70), duration(20))
                )
            )
            event.isCancelled = true
            return
        }
        // Inventory has enough space, so continue
        player.playSound(player.location, Sound.ENTITY_ITEM_PICKUP, 0.5f, 1.2f)
        val blockLocation = event.block.location
        val originalType = event.block.type

        // DO NOT CHANGE (Block must generate after 1 tick)
        plugin.server.scheduler.runTaskLater(plugin, Runnable {
            blockLocation.block.type = originalType
        }, 1L)
    }

    private fun getBlockDrops(block: org.bukkit.block.Block): List<ItemStack> {
        return listOf(ItemStack(block.type))
    }
}