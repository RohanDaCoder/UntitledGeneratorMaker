package me.rohandacoder.untitledGeneratorMaker.util

import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import me.rohandacoder.untitledGeneratorMaker.commands.HelloCommand
import me.rohandacoder.untitledGeneratorMaker.commands.PingCommand
import org.bukkit.command.CommandSender

class CommandLoader(private val plugin: UntitledGeneratorMaker) {
    private lateinit var liteCommands: LiteCommands<CommandSender>

    fun loadCommands() {
        plugin.logger.info("Loading commands...")
        liteCommands = LiteBukkitFactory.builder(plugin)
            .commands(PingCommand(), HelloCommand())
            .build()
        plugin.logger.info("Loaded commands.")
    }
}
