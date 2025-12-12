package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.command.CommandSender

@Command(name = "ugm")
@Suppress("UNUSED")
class UgmCommand(private val plugin: UntitledGeneratorMaker) {
    @Execute()
    @Suppress("UNUSED")
    fun execute(@Sender sender: CommandSender) {
        sender.sendRichMessage("<dark_gray>==================================================")
        sender.sendRichMessage("<yellow><bold> Untitled Generator Maker <gray>${plugin.pluginMeta.version}")
        sender.sendRichMessage("<gray>   By <blue>${plugin.pluginMeta.authors} ")
        sender.sendRichMessage("<white>  <hover:show_text:\"<blue>(CLICK ME)\"><click:open_url:'https://github.com/RohanDaCoder/UntitledGeneratorMaker'>[GITHUB]</click> ")
        sender.sendRichMessage("<green> a gen server maker ")
        sender.sendRichMessage("<dark_gray>==================================================")
    }

    @Execute(name = "reload")
    @Permission("untitledgeneratormaker.reload")
    @Suppress("UNUSED")
    fun executeReload(@Sender sender: CommandSender) {
        plugin.reloadConfig()
        sender.sendRichMessage(
            plugin.config.getString("messages.reload") ?: "<green>Configuration reloaded!"
        )
        plugin.logger.info("Config reloaded!")
    }
}
