package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import org.bukkit.command.CommandSender

@Command(name = "hello")
class HelloCommand {
    @Suppress("UNUSED")
    @Execute
    fun executeHello(
        @Sender sender: CommandSender,
    ) {
        // Execute the /hello
        sender.sendRichMessage("<green>Hello, ${sender.name}")
    }

}
