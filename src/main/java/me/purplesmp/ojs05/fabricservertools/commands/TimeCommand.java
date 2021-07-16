package me.purplesmp.ojs05.fabricservertools.commands;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;

public class TimeCommand {
    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(literal("time")
                .requires(Permissions.require("tools.time.main"))
                .executes(context -> {
                    long time = context.getSource().getPlayer().world.getTime();
                    
                    context.getSource().sendFeedback(new LiteralText(Formatting.AQUA + "The time  is " + Formatting.GOLD + time), false);
                    return 1;
                })
        ));
    }
}
