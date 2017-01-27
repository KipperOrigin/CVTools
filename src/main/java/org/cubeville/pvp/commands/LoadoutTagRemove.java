package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandParameterLoadout;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutTagRemove extends Command {

	public LoadoutTagRemove() {
		super("loadout tag remove");
		setPermission("pvp.loadout.commands");
		addBaseParameter(new CommandParameterLoadout());
		addBaseParameter(new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		LoadoutContainer loadout = (LoadoutContainer) baseParameters.get(0);
		loadout.removeTag((String) baseParameters.get(1));
		
		return new CommandResponse("&aTag successfully remove!");
	}
}
