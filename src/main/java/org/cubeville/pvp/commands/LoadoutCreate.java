package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.cvtools.CVTools;

public class LoadoutCreate extends Command {
    
    public LoadoutCreate() {
        super("loadout create");
        addBaseParameter(new CommandParameterString());
        addParameter("team", true, new CommandParameterString());
    }
    
    @Override
    public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        if (!parameters.containsKey("team")) {
            if (!CVTools.getInstance().getLoadoutManager().createLoadout(player, (String) baseParameters.get(0)))
                player.sendMessage("Loadout already exists!");
        } else if (CVTools.getInstance().getLoadoutManager().getLoadout((String) baseParameters.get(0)) != null) {
            CVTools.getInstance().getLoadoutManager().getLoadout((String) baseParameters.get(0)).createInventory(player, (String) parameters.get("team"));
        }
    }
    
}
