package me.rohandacoder.untitledGeneratorMaker.commands

import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import org.bukkit.command.CommandSender


@Command(name = "ping")
class PingCommand {
    @Suppress("UNUSED")
    @Execute
    fun execute(
        @Sender sender: CommandSender,
        @Arg("player") playerName: String,
        @Arg("times?") times: Int = 1
    ) {
        val player = sender.server.getPlayer(playerName)

        if (player == null) {
            sender.sendRichMessage("<red>Player $playerName not found!")
            return
        }

        val ping = player.ping
        repeat(times.coerceAtMost(5)) {
            sender.sendRichMessage("<green>${player.name}'s ping: <yellow>$ping ms")
        }
    }
}
