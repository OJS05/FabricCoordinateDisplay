package me.purplesmp.ojs05.fabricservertools.commands;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import static net.minecraft.server.command.CommandManager.literal;

public class PortalCommand {
    public static void register(){
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(literal("portal")
            .requires(Permissions.require("tools.portal.main"))
                .executes(context -> {

                    ServerPlayerEntity sender = context.getSource().getPlayer();

                    final int x = sender.getBlockX();
                    final int y = sender.getBlockY();
                    final int z = sender.getBlockZ();

                    int finalX = 0;
                    int finalZ = 0;

                    if(sender.world.getRegistryKey().equals(World.NETHER)){
                        finalX = x * 8;
                        finalZ = z * 8;
                    }

                    if(sender.world.getRegistryKey().equals(World.OVERWORLD)){
                        finalX = x / 8;
                        finalZ = z / 8;
                    }

                    context.getSource().sendFeedback(new LiteralText(Formatting.AQUA + "You should place your portal at " + Formatting.GOLD + finalX + ", " + y + ", " + finalZ), false);

                    return 1;
                })
        )));
    }
}
