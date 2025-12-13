package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.command.CommandSender

@Command(name = "test")
@Permission("untitledgeneratormaker.test")
class TestCommand(private val plugin: UntitledGeneratorMaker) {

    @Execute
    @Suppress("UNUSED")
    fun executeTest(@Sender sender: CommandSender) {
        sender.sendRichMessage("TEST")
    }

}
