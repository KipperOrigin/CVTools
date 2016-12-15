package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.commandmap.CommandMap;
import org.cubeville.cvtools.commands.commandmap.CommandMapManager;
import org.cubeville.cvtools.commands.commandmap.CommandMapValueException;

public class EntityName extends Command {

	public EntityName() {
		super("entity name");
		addParameter("name", true, new CommandParameterString());
		addFlag("remove");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		
		CommandMap commandMap = CommandMapManager.primaryMap;
		Entity entity = null;
		
		try {
			entity = (Entity) commandMap.getUnsafe(player);
		} catch (CommandMapValueException e) {
			player.sendMessage(e.toString());
		}
		
		if (parameters.containsKey("name") && !flags.contains("remove")) {
			entity.setCustomName(Colorize.addColor((String) parameters.get("name")));
			entity.setCustomNameVisible(true);
		} else if (!parameters.containsKey("name") && flags.contains("remove")) {
			entity.setCustomName("");
			entity.setCustomNameVisible(false);
		}
                return null;
	}
}
