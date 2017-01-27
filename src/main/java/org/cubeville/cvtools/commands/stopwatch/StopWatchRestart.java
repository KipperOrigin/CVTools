package org.cubeville.cvtools.commands.stopwatch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class StopWatchRestart extends Command {

	public StopWatchRestart() {
		super("stopwatch restart");
        setPermission("cvtools.stopwatch");
		addParameter("name", true, new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		String name = "";
		
		if (!commandMap.contains(player) || !(commandMap.get(player) instanceof Long))
			throw new CommandExecutionException("&cYou have not started a stopwatch!");
		if (parameters.containsKey(player.getName()))
			name += " for &6" + (String) parameters.get("name") + " ";
		
		double time = (System.currentTimeMillis() - (Long) commandMap.get(player))/ 1000.0;
		
		commandMap.put(player, System.currentTimeMillis());
		
		return new CommandResponse("&aFinal Time" + name + "&f: " + time);
	}
}

