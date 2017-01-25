package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutTagClear extends Command {

	public LoadoutTagClear() {
		super("loadout tag clear");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		LoadoutContainer loadout = (LoadoutContainer) baseParameters.get(0);
		loadout.clearTags();
		
		return new CommandResponse("&aAll tags successfully removed!");
	}
}
