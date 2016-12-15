package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntitySilent extends Command {

	public EntitySilent() {
		super("entity silent");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Entity> commandMap = CommandMapManager.getEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6entity&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6entity&c!"));
			return null;
		}
		
		if (commandMap.get(player.getName()).isSilent())
			commandMap.get(player.getName()).setSilent(false);
		else
			commandMap.get(player.getName()).setSilent(true);
                return null;
	}
}
