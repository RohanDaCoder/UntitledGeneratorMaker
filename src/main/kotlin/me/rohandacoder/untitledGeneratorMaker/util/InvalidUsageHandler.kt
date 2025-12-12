package me.rohandacoder.untitledGeneratorMaker.util

import dev.rollczi.litecommands.handler.result.ResultHandlerChain
import dev.rollczi.litecommands.invalidusage.InvalidUsage
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler
import dev.rollczi.litecommands.invocation.Invocation
import org.bukkit.Sound
import org.bukkit.command.CommandSender

class InvalidUsageHandler : InvalidUsageHandler<CommandSender> {

    override fun handle(
        invocation: Invocation<CommandSender>,
        result: InvalidUsage<CommandSender>,
        chain: ResultHandlerChain<CommandSender>?
    ) {
        val sender = invocation.sender()
        val schematic = result.schematic
        if (sender is org.bukkit.entity.Player) {
            sender.playSound(
                sender.location, Sound.BLOCK_ANVIL_FALL, 1.0f, 1.0f
            )
        }
        if (schematic.isOnlyFirst) {
            sender.sendRichMessage("<red>Invalid usage!")
            sender.sendRichMessage("<dark_gray> - <gray>${schematic.first()}")
            return
        }

        sender.sendRichMessage("<red>Invalid usage of command!")
        for (scheme in schematic.all()) {
            sender.sendRichMessage("<dark_gray> - <gray>$scheme")
        }
    }
}
