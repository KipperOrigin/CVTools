package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterListString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandParameterLoadout;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutTagAdd extends Command {

	public LoadoutTagAdd() {
		super("loadout tag add");
		addBaseParameter(new CommandParameterLoadout());
		addBaseParameter(new CommandParameterListString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		LoadoutContainer loadout = (LoadoutContainer) baseParameters.get(0);
		loadout.addTags((List<String>) baseParameters.get(1));
		
		return new CommandResponse("&aTag(s) successfully added!");
	}

}
