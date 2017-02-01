package org.cubeville.cvtools.commands.stopwatch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMapManager;

public class StopWatchStart extends Command {

	public StopWatchStart() {
		super("stopwatch start");
        setPermission("cvtools.stopwatch");
        addParameter("title", true, new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
	    Map<String, Long> stopwatchMap = CommandMapManager.stopwatchMap;
		
	    if (parameters.containsKey("title")) {
	    	if (stopwatchMap.containsKey(parameters.get("title"))) throw new CommandExecutionException("&cStopwatch already started!");
	    	stopwatchMap.put((String) parameters.get("title"), System.currentTimeMillis());
	    } else {
	    	if (stopwatchMap.containsKey(player.getName().toUpperCase()))
	    		throw new CommandExecutionException("&cStopwatch already started!");
	    	stopwatchMap.put(player.getName(), System.currentTimeMillis());
	    }
	    
		return new CommandResponse("&aStopwatch started!");
	}
}
