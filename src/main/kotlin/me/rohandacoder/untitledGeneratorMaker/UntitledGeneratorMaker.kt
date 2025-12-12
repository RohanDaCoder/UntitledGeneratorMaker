package me.rohandacoder.untitledGeneratorMaker

import me.rohandacoder.untitledGeneratorMaker.util.CommandLoader
import me.rohandacoder.untitledGeneratorMaker.util.EventLoader
import org.bukkit.plugin.java.JavaPlugin

class UntitledGeneratorMaker : JavaPlugin() {

    override fun onEnable() {
        // Load Config
        saveDefaultConfig()
        reloadConfig()

        // Load Events, Commands
        EventLoader(this).loadEvents()
        CommandLoader(this).loadCommands()

        // Finish
        logger.info("Enabled!")
    }

    override fun onDisable() {
        EventLoader(this).unloadEvents()
        CommandLoader(this).unloadCommands()
        logger.info("Disabled!")
    }
    override fun reloadConfig() {
        super.reloadConfig()
    }
}
