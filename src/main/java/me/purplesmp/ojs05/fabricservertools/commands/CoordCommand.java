package me.purplesmp.ojs05.fabricservertools.commands;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CoordCommand {
    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(literal("coords")
                .requires(Permissions.require("tools.coords.main"))
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    ServerPlayerEntity sender = source.getPlayer();

                    final int x = sender.getBlockX();
                    final int y = sender.getBlockY();
                    final int z = sender.getBlockZ();
                    final String player = sender.getDisplayName().getString().formatted(Formatting.GOLD);

                    String world = null;

                    if(sender.world.getRegistryKey().equals(World.OVERWORLD)){
                        world = " Overworld";
                    }
                    if(sender.world.getRegistryKey().equals(World.NETHER)){
                        world = " Nether";
                    }
                    if(sender.world.getRegistryKey().equals(World.END)){
                        world = " End";
                    }

                    sender.getServer().getPlayerManager().broadcastChatMessage(new LiteralText(Formatting.GOLD + player + Formatting.AQUA + "'s coordinates are " + Formatting.GOLD + x + ", " + y + ", " + z + Formatting.AQUA + " in the" + Formatting.GOLD + world), MessageType.CHAT,sender.getUuid());
                    return 1;
                })
                .then(argument("target",EntityArgumentType.player())
                        .requires(Permissions.require("tools.coords.main"))
                        .executes(context -> {

                            ServerPlayerEntity sender = context.getSource().getPlayer();
                            final String senderName = sender.getDisplayName().getString().formatted(Formatting.GOLD);
                            ServerPlayerEntity target = EntityArgumentType.getPlayer(context,"target");

                            final int x = sender.getBlockX();
                            final int y = sender.getBlockY();
                            final int z = sender.getBlockZ();

                            String world = null;

                            if(sender.world.getRegistryKey().equals(World.OVERWORLD)){
                                world = " Overworld";
                            }
                            if(sender.world.getRegistryKey().equals(World.NETHER)){
                                world = " Nether";
                            }
                            if(sender.world.getRegistryKey().equals(World.END)){
                                world = " End";
                            }
                            
                            target.sendMessage(new LiteralText(Formatting.GOLD + senderName + Formatting.AQUA + "'s coordinates are " + Formatting.GOLD + x + ", " + y + ", " + z + Formatting.AQUA + " in the" + Formatting.GOLD + world),MessageType.CHAT, sender.getUuid());

                            return 1;
                        })
                )
                .then(literal("search")
                        .then(argument("target", EntityArgumentType.player())
                                .requires(Permissions.require("tools.coords.moderator"))
                                .executes(context -> {
                                    ServerPlayerEntity requestedPlayer = EntityArgumentType.getPlayer(context,"target");
                                    final int x = requestedPlayer.getBlockX();
                                    final int y = requestedPlayer.getBlockY();
                                    final int z = requestedPlayer.getBlockZ();
                                    final String player = requestedPlayer.getDisplayName().getString().formatted(Formatting.GOLD);

                                    String world = null;

                                    if(requestedPlayer.world.getRegistryKey().equals(World.OVERWORLD)){
                                        world = " Overworld";
                                    }
                                    if(requestedPlayer.world.getRegistryKey().equals(World.NETHER)){
                                        world = " Nether";
                                    }
                                    if(requestedPlayer.world.getRegistryKey().equals(World.END)){
                                        world = " End";
                                    }

                                    context.getSource().sendFeedback(new LiteralText(Formatting.GOLD + player + Formatting.AQUA + "'s coordinates are " + Formatting.GOLD + x + ", " + y + ", " + z + Formatting.AQUA + " in the" + Formatting.GOLD + world), true);
                                    return 1;
                                })
                        )
                )
        ));
    }
}
