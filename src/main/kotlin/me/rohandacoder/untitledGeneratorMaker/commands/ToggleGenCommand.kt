package me.rohandacoder.untitledGeneratorMaker.commands

import com.jeff_media.customblockdata.CustomBlockData
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.persistence.PersistentDataType

@Command(name = "togglegen")
@Permission("untitledgeneratormaker.togglegenblocks")
class ToggleGenCommand(private val plugin: UntitledGeneratorMaker) {

    @Execute(name = "view")
    @Suppress("UNUSED")
    fun executeView(@Sender sender: CommandSender) {
        val player = plugin.server.getPlayer(sender.name)
        val block = player?.getTargetBlockExact(5)
        if (block == null) {
            player?.sendRichMessage("<red>Block not found!")
            return
        }
        val customBlockData = CustomBlockData(block, plugin)
        val generatorKey = NamespacedKey(plugin, "shouldRegenerate")
        val value = customBlockData.get(generatorKey, PersistentDataType.BOOLEAN) ?: false
        sender.sendRichMessage("<aqua><bold>BLOCK INFO")
        sender.sendRichMessage("<yellow> Block: <gray>${block.blockData.material}")
        sender.sendRichMessage("<yellow> Location: <gray>${block.location}")
        sender.sendRichMessage("<yellow> shouldRegenerate: <gray>$value")
    }

    @Execute(name = "toggle")
    @Suppress("UNUSED")
    fun executeToggleGen(@Sender sender: CommandSender) {
        val player = plugin.server.getPlayer(sender.name)
        val block = player?.getTargetBlockExact(5)
        if (block == null) {
            player?.sendRichMessage("<red>Block not found!")
            return
        }
        val customBlockData = CustomBlockData(block, plugin)
        val generatorKey = NamespacedKey(plugin, "shouldRegenerate")
        val value = customBlockData.get(generatorKey, PersistentDataType.BOOLEAN) ?: false
        customBlockData.set(generatorKey, PersistentDataType.BOOLEAN, !value)
        sender.sendRichMessage("<green>Successfully toggled regeneration of <yellow>${block.blockData.material} <gray>at <yellow>${block.location}")
        sender.sendRichMessage("<yellow>After: <gray>$value")
    }

}
