package me.rohandacoder.untitledGeneratorMaker.listeners

import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener(private val plugin: UntitledGeneratorMaker) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        plugin.logger.info("JOIN")
        val player = event.player
        plugin.server.scheduler.runTaskLater(plugin, Runnable {
            if (player.isOnline) {
                player.sendRichMessage("<green>Welcome, ${player.name}!")
            }
        }, 40L)

    }
}
