package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.pvp.commands.commandparameters.CommandParameterLoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutHandler;

public class LoadoutClone extends Command {

    public LoadoutClone() {
        super("loadout clone");
        addBaseParameter(new CommandParameterLoadoutContainer());
        addParameter("team", true, new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        String team = "main";
        LoadoutContainer lc = (LoadoutContainer) baseParameters.get(0);
        
        if (parameters.containsKey("team")) {
            team = (String) parameters.get("team");
        }
        
        if (!lc.containsInventory(team)) throw new CommandExecutionException("");

        LoadoutHandler.cloneInventoryToLoadout(player, lc, team);
        
        return null;
    }

}
