package me.rohandacoder.untitledGeneratorMaker.util

import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import me.rohandacoder.untitledGeneratorMaker.listeners.JoinListener
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class EventLoader(private val plugin: UntitledGeneratorMaker) {

    private val listeners: MutableList<Listener> = mutableListOf()

    fun loadEvents() {
        register(JoinListener(plugin))
        plugin.logger.info("Registered listeners.")
    }

    fun unloadEvents() {
        if (listeners.isEmpty()) return
        HandlerList.unregisterAll(plugin)
        listeners.clear()
        plugin.logger.info("Unregistered all listeners.")
    }

    private fun register(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin)
        listeners += listener
    }
}
