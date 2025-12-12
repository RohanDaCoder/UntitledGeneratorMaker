package me.rohandacoder.untitledGeneratorMaker.util

import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import me.rohandacoder.untitledGeneratorMaker.commands.HelloCommand
import me.rohandacoder.untitledGeneratorMaker.commands.PingCommand
import org.bukkit.command.CommandSender

class CommandLoader(private val plugin: UntitledGeneratorMaker) {
    private var liteCommands: LiteCommands<CommandSender>? = null

    fun loadCommands() {
        liteCommands = LiteBukkitFactory.builder(plugin)
            .commands(PingCommand(), HelloCommand())
            .invalidUsage(InvalidUsageHandler())
            .build()
        plugin.logger.info("Loaded commands.")
    }

    fun unloadCommands() {
        liteCommands?.unregister()
        liteCommands = null
        plugin.logger.info("Unloaded commands.")
    }
}
