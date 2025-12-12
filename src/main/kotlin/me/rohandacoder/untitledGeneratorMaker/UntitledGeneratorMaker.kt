package me.rohandacoder.untitledGeneratorMaker

import org.bukkit.plugin.java.JavaPlugin

class UntitledGeneratorMaker : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Enabled!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Disabling")
    }
}
