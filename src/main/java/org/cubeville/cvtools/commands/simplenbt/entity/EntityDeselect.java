package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityDeselect extends Command {

	public EntityDeselect() {
		super("entity deselect");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Entity> commandMap = CommandMapManager.getEntityCommandMap();
		if (commandMap.containsKey(player.getName())) {
			if (commandMap.get(player.getName()) == null) {
				player.sendMessage(Colorize.addColor("&cSelection cancelled!"));
			} else
				if (commandMap.get(player.getName()).getCustomName() != null)
					player.sendMessage(Colorize.addColor("&cEntity &6" + commandMap.get(player.getName()).getCustomName() + "&c deselected!"));
				else
					player.sendMessage(Colorize.addColor("&cEntity &6" + commandMap.get(player.getName()).getName() + "&c deselected!"));
			
			commandMap.remove(player.getName());
		} else {
			player.sendMessage(Colorize.addColor("&cCannot cancel invalid block selection!"));
		}
	}

}
