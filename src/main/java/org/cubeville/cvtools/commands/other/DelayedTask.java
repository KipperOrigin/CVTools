package org.cubeville.cvtools.commands.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
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
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		int delay = (int) baseParameters.get(0);
		
		if (!flags.contains("ticks")) {
			delay *= 20;
		}
		
		if ((parameters.containsKey("cmd") && parameters.containsKey("chat")) || (!parameters.containsKey("cmd") && !parameters.containsKey("chat"))) {
			throw new CommandExecutionException("cMust delay a command OR chat!");
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				runDelayedTask(player, parameters);
				player.sendMessage(Colorize.addColor("&aAction successfully played!"));
			}
			
		}, delay);
		return new CommandResponse("&aAction successfully delayed!");
	}
	
	public void runDelayedTask(Player player, Map<String, Object> parameters) {
		if (parameters.containsKey("cmd")) {
			player.performCommand((String) parameters.get("cmd"));
		} else if (parameters.containsKey("chat")) {
			player.chat((String) parameters.get("chat"));
		} else {
			throw new IllegalArgumentException();
		}
	}
}
