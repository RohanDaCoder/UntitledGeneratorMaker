package me.rohandacoder.untitledGeneratorMaker.listeners

import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.block.CreatureSpawner
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class BlockBreakListener(private val plugin: UntitledGeneratorMaker) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onBreak(event: BlockBreakEvent) {
        val player: Player = event.player
        val block: Block = event.block

        val allowedGeneratorsString = plugin.config.getString("generators.allowed-blocks", "")
        val allowedGeneratorsList = allowedGeneratorsString?.split(",")?.map { it.trim() }

        if (allowedGeneratorsList?.contains(block.type.name) != true ) {
            return
        }

        val drops: List<ItemStack> = when (block.type) {
            Material.SPAWNER -> {
                val state = block.state
                if (state is CreatureSpawner) {
                    listOf(ItemStack(Material.SPAWNER))
                } else {
                    emptyList()
                }
            }
            else -> {
                listOf(ItemStack(block.type))
            }
        }

        for (drop in drops) {
            if (!hasInventorySpace(player, drop)) {
                player.sendActionBar(
                    Component.text("Inventory full - can't collect drop!").color(NamedTextColor.RED)
                )
                event.isCancelled = true
                return
            }
        }

        drops.forEach { item ->
            player.inventory.addItem(item)
            player.playSound(player.location, Sound.ENTITY_ITEM_PICKUP, 0.5f, 1.2f)
        }

        player.sendActionBar(
            Component.text("Generator dropped its contents!").color(NamedTextColor.GREEN)
        )

        event.isCancelled = true
    }

    private fun hasInventorySpace(player: Player, itemStack: ItemStack): Boolean {
        if (player.inventory.firstEmpty() != -1) return true

        val itemType = itemStack.type
        val maxStack = itemStack.maxStackSize

        for (i in 0 until player.inventory.size) {
            val existing = player.inventory.getItem(i)
            if (existing?.type == itemType && existing.amount < maxStack) {
                return true
            }
        }
        return false
    }
}