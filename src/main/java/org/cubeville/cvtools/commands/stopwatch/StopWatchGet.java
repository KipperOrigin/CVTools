package org.cubeville.cvtools.commands.stopwatch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class StopWatchGet extends Command {

	public StopWatchGet() {
		super("stopwatch time");
		addParameter("name", true, new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Long> commandMap = CommandMapManager.getStopWatchCommandMap();
		
		String name = "";
		
		if (!commandMap.containsKey(player.getName()))
			return;
		if (parameters.containsKey(player.getName()))
			name += " for &6" + (String) parameters.get("name") + " ";
		
		double time = (System.currentTimeMillis() - commandMap.get(player.getName()))/ 1000.0;
		
		player.sendMessage(Colorize.addColor("&aCurrent Time" + name + "&f: " + time));
	}
}
