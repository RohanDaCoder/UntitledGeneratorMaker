package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.shortcut.Shortcut
import org.bukkit.command.CommandSender

@Command(name = "hello")
class HelloCommand {
    @Suppress("UNUSED")
    @Shortcut("hi")
    @Execute
    fun executeHello(
        @Sender sender: CommandSender,
    ) {
        sender.sendRichMessage("<green>Hello, ${sender.name}")
    }

}
