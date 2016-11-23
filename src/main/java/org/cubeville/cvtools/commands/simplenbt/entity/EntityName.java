package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.utils.Colorize;

public class EntityName extends Command {

	public EntityName() {
		super("entity name");
		addParameter("name", true, new CommandParameterString());
		addFlag("remove");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		}
		
		LivingEntity entity = commandMap.get(player.getName());
		
		if (parameters.containsKey("name") && !flags.contains("remove")) {
			entity.setCustomName(Colorize.addColor((String) parameters.get("name")));
			entity.setCustomNameVisible(true);
		} else if (!parameters.containsKey("name") && flags.contains("remove")) {
			entity.setCustomName("");
			entity.setCustomNameVisible(false);
		}
			
	}
}
