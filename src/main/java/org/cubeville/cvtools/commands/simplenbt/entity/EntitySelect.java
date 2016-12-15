package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.commandmap.CommandMap;
import org.cubeville.cvtools.commands.commandmap.CommandMapManager;

public class EntitySelect extends Command {

	public EntitySelect() {
		super("entity select");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		
		if (!commandMap.contains(player)) {
			commandMap.put(player, null);
			player.sendMessage(Colorize.addColor("&aYou may now select a &6entity&a!"));
		} else
			player.sendMessage(Colorize.addColor("&cYou are already selecting an &6entity&c!"));
                return null;
	}

}

