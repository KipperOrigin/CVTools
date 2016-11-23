package org.cubeville.cvtools.commands.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.cvtools.CVTools;

public class DelayedTask extends Command {

	CVTools plugin;
	
	public DelayedTask(CVTools plugin) {
		super("delay");
		addBaseParameter(new CommandParameterInteger());
		addParameter("cmd", true, new CommandParameterString());
		addParameter("chat", true, new CommandParameterString());
		this.plugin = plugin;
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		int delay = (int) baseParameters.get(0);
		
		if (!flags.contains("ticks"))
			delay *= 20;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				runDelayedTask(player, parameters);
			}
			
		}, delay);
	}
	
	public void runDelayedTask(Player player, Map<String, Object> parameters) {
		if (parameters.containsKey("cmd"))
			player.performCommand((String) parameters.get("cmd"));
		else if (parameters.containsKey("chat"))
			player.chat((String) parameters.get("chat"));
	}
}
