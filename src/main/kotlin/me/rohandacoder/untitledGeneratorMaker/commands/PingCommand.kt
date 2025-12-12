package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(name = "ping")
class PingCommand {
    @Suppress("UNUSED")
    @Execute
    fun execute(@Sender sender: CommandSender) {
        val player = sender as? Player
        player?.sendRichMessage("<green>${player.name}'s ping: <yellow>${player.ping} ms")
    }
}
