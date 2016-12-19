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

public class LoadoutEdit extends Command{

	public LoadoutEdit() {
		super("loadout edit");
		addBaseParameter(new CommandParameterString());
		addParameter("team", true, new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		LoadoutContainer loadout = CVTools.getInstance().getLoadoutManager().getLoadoutByName((String) baseParameters.get(0));
		String loadoutName = "main";
		
		if (!CVTools.getInstance().getLoadoutManager().contains((String) baseParameters.get(0))) {
			throw new CommandExecutionException("&cNo loadout exists!");
		}
		
		if (parameters.containsKey("team")) {
			loadoutName = (String) parameters.get("team");
		}
		
		if (!loadout.editInventory(player, loadoutName)) {
			throw new CommandExecutionException("&cNo loadout exists!");
		}

                return null;
	}

}
