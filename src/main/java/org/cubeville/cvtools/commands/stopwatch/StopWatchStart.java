package org.cubeville.cvtools.commands.stopwatch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.cvtools.commands.CommandMapManager;

public class StopWatchStart extends Command {

	public StopWatchStart() {
		super("stopwatch start");
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, Long> commandMap = CommandMapManager.getStopWatchCommandMap();
		
		commandMap.put(player.getName(), System.currentTimeMillis());
	}
}
