package me.rohandacoder.untitledGeneratorMaker.util

import SpawnCommand
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import me.rohandacoder.untitledGeneratorMaker.commands.HelloCommand
import me.rohandacoder.untitledGeneratorMaker.commands.PingCommand
import me.rohandacoder.untitledGeneratorMaker.commands.TestCommand
import me.rohandacoder.untitledGeneratorMaker.commands.ToggleGenCommand
import me.rohandacoder.untitledGeneratorMaker.commands.UgmCommand
import org.bukkit.command.CommandSender

class CommandLoader(private val plugin: UntitledGeneratorMaker) {
    private var liteCommands: LiteCommands<CommandSender>? = null

    fun loadCommands() {
        liteCommands = LiteBukkitFactory.builder(plugin).commands(
            PingCommand(),
            HelloCommand(),
            SpawnCommand(plugin),
            UgmCommand(plugin),
            TestCommand(),
            ToggleGenCommand(plugin)
        ).invalidUsage(InvalidUsageHandler()).build()
        plugin.logger.info("Loaded commands.")
    }

    fun unloadCommands() {
        liteCommands?.unregister()
        liteCommands = null
        plugin.logger.info("Unloaded commands.")
    }
}
