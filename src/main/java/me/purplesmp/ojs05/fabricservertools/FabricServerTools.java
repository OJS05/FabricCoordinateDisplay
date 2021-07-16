package me.purplesmp.ojs05.fabricservertools;

import me.purplesmp.ojs05.fabricservertools.commands.CoordCommand;
import me.purplesmp.ojs05.fabricservertools.commands.PortalCommand;
import me.purplesmp.ojs05.fabricservertools.commands.TimeCommand;
import net.fabricmc.api.ModInitializer;

public class FabricServerTools implements ModInitializer {

    @Override
    public void onInitialize() {
        CoordCommand.register();
        PortalCommand.register();
        TimeCommand.register();
    }



}
