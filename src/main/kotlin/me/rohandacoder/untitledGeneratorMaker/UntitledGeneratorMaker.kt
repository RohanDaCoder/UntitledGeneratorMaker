package me.rohandacoder.untitledGeneratorMaker

import me.rohandacoder.untitledGeneratorMaker.util.CommandLoader
import org.bukkit.plugin.java.JavaPlugin

class UntitledGeneratorMaker : JavaPlugin() {

    override fun onEnable() {
        CommandLoader(this).loadCommands()
        logger.info("UntitledGeneratorMaker enabled!")
    }

    override fun onDisable() {
        CommandLoader(this).unloadCommands()
        logger.info("UntitledGeneratorMaker disabled!")
    }
}
