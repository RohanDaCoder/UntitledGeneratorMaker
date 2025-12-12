package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.command.CommandSender

@Command(name = "ugm")
@Suppress("UNUSED")
class UgmCommand(
    private val plugin: UntitledGeneratorMaker
) {
    private val githubUrl = "https://github.com/RohanDaCoder/UntitledGeneratorMaker"
    private val separatorLine = "=".repeat(50)

    @Execute
    @Suppress("UNUSED")
    fun execute(@Sender sender: CommandSender) {
        sender.sendRichMessage("<dark_gray>$separatorLine")
        sender.sendRichMessage("<yellow><bold>Untitled Generator Maker <gray>${plugin.pluginMeta.version}")
        sender.sendRichMessage("    <gray>By <blue>${plugin.pluginMeta.authors.joinToString(", ")}")
        sender.sendRichMessage("<white><italic><hover:show_text:'<blue>Click to visit GitHub'><click:open_url:'$githubUrl'>[Github Repository]</click></hover>")
        sender.sendRichMessage(" ")
        sender.sendRichMessage("<green>a gen server maker")
        sender.sendRichMessage(" ")
        sender.sendRichMessage("<dark_gray>$separatorLine")
    }

    @Execute(name = "reload")
    @Permission("untitledgeneratormaker.reload")
    @Suppress("UNUSED")
    fun executeReload(@Sender sender: CommandSender) {
        try {
            plugin.reloadConfig()
            val configMessage = plugin.config.getString("messages.reload")
            val customMessage: String = configMessage ?: "<green>Configuration reloaded successfully!"
            sender.sendRichMessage(customMessage)
            plugin.logger.info("Config reloaded by ${sender.name}")
        } catch (e: Exception) {
            sender.sendRichMessage("<red>An error occurred while reloading the config!")
            plugin.logger.severe("Error reloading config! $e")
            e.printStackTrace()
        }
    }
}