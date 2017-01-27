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
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutRemove extends Command{

	public LoadoutRemove(){
		super("loadout remove");
		setPermission("pvp.loadout.commands");
		addBaseParameter(new CommandParameterString());
		addParameter("team", true, new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		if (!CVTools.getInstance().getLoadoutManager().contains((String) baseParameters.get(0)))
			throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0).toString().toLowerCase() + "&c does not exist!");
		
		if (parameters.containsKey("team")) {
			LoadoutContainer lc = CVTools.getInstance().getLoadoutManager().getLoadoutByName((String) baseParameters.get(0));
			
			if (!lc.containsInventory((String) parameters.get("team")))
				throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0).toString().toLowerCase() + "&c does not contain sub loadout &6" + parameters.get("team"));
			
			lc.removeInventory((String) parameters.get("team"));
			return new CommandResponse("&aSuccessfully removed sub loadout &6" + parameters.get("team") + "&a from loadout &6" + baseParameters.get(0).toString().toLowerCase());
		
		} else {
			CVTools.getInstance().getLoadoutManager().removeLoadout((String) baseParameters.get(0));
			return new CommandResponse("&aSuccessfully removed loadout &6" + baseParameters.get(0).toString().toLowerCase());
		}
	}
}
