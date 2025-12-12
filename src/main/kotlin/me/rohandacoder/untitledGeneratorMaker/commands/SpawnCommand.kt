import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Sender
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.shortcut.Shortcut
import me.rohandacoder.untitledGeneratorMaker.UntitledGeneratorMaker
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(name = "spawn")
class SpawnCommand(private val plugin: UntitledGeneratorMaker) {

    @Execute
    @Shortcut("lobby", "hub")
    @Suppress("UNUSED")
    fun executeSpawn(@Sender sender: CommandSender) {
        val player = sender as? Player ?: run {
            sender.sendRichMessage("<red>Only players can use this command.")
            return
        }
        teleportToSpawn(
            plugin, player
        )
    }

    companion object {
        fun teleportToSpawn(plugin: UntitledGeneratorMaker, player: Player) {
            val worldName = plugin.config.getString("spawn.world", "world") ?: "world"
            val world = plugin.server.getWorld(worldName) ?: player.world

            val x = plugin.config.getDouble("spawn.x", 0.5)
            val y = plugin.config.getDouble("spawn.y", 100.0)
            val z = plugin.config.getDouble("spawn.z", 0.5)
            val yaw = plugin.config.getDouble("spawn.yaw", 0.0).toFloat()
            val pitch = plugin.config.getDouble("spawn.pitch", 0.0).toFloat()

            val spawnLocation = Location(world, x, y, z, yaw, pitch)
            player.teleport(spawnLocation)
            player.sendRichMessage(
                plugin.config.getString("messages.spawn") ?: "<green>Teleported to spawn!"
            )
        }
    }
}
