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

public class StopWatchStop extends Command {

	public StopWatchStop() {
		super("stopwatch stop");
        setPermission("cvtools.stopwatch");
		addParameter("name", true, new CommandParameterString());
	    addParameter("title", true, new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
	    Map<String, Long> stopwatchMap = CommandMapManager.stopwatchMap;
	    String stopwatch = "";
	    String name = "";
	    
	    if (parameters.containsKey("title") && player.hasPermission("cvtools.stopwatch.custom")) {
	        if (!stopwatchMap.containsKey(parameters.get("title"))) throw new CommandExecutionException("");
	        else stopwatch = (String) parameters.get("title");
	    } else if (stopwatchMap.containsKey(player.getName())) {
	        stopwatch = player.getName();
	    } else {
	        throw new CommandExecutionException("");
	    }
	    
	    if (parameters.containsKey("name")) name = " for " + parameters.get("name") + " ";

        stopwatchMap.remove(stopwatch);
	    double time = (System.currentTimeMillis() - stopwatchMap.get(stopwatch)) / 1000.0;
		
		return new CommandResponse("&6" + stopwatch + " &aFinal Time" + name + "&f: " + time);
	}
}
