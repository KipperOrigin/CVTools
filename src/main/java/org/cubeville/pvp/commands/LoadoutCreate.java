package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;

public class LoadoutCreate extends Command {
    
    public LoadoutCreate() {
        super("loadout create");
        addBaseParameter(new CommandParameterString(CommandParameterString.NO_SPECIAL_CHARACTERS));
        addParameter("team", true, new CommandParameterString(CommandParameterString.NO_SPECIAL_CHARACTERS));
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        if (!parameters.containsKey("team")) {
            if (!CVTools.getInstance().getLoadoutManager().createLoadout(player, (String) baseParameters.get(0))) {
                throw new CommandExecutionException("&cLoadout already exists!");
            } else {
            	return new CommandResponse("&aLoadout &6" + baseParameters.get(0).toString().toLowerCase() + "&a created!");
            }
        } else if (CVTools.getInstance().getLoadoutManager().getLoadoutByName((String) baseParameters.get(0)) != null) {
            CVTools.getInstance().getLoadoutManager().getLoadoutByName((String) baseParameters.get(0)).createInventory(player, (String) parameters.get("team"));
            return new CommandResponse("&aSub Loadout &6" + (String) parameters.get("team") + "&a for loadout &6" + (String) baseParameters.get(0) + "&a created!");
        } else {
        	throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0) + " &cdoes not exist! Could not create sub-loadout &6" + parameters.get("team"));
        }
    }
    
}
