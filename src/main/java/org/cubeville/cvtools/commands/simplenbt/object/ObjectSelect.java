package org.cubeville.cvtools.commands.simplenbt.object;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMapManager;

public class ObjectSelect extends Command {

	public ObjectSelect() {
		super("select");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		if (!CommandMapManager.primaryMap.contains(player)) {
			CommandMapManager.primaryMap.put(player, null);
			return new CommandResponse("&aYou may now select an &6Object&a!");
		} else {
			throw new CommandExecutionException("&cYou are already selecting an &6Object&c!");
		}
	}

}
