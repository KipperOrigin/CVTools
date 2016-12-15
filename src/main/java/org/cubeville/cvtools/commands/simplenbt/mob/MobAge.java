package org.cubeville.cvtools.commands.simplenbt.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobAge extends Command {

	public MobAge() {
		super("mob age");
		addParameter("set", true, new CommandParameterInteger());
		addFlag("baby");
		addFlag("adult");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6adeable mob&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null || !Ageable.class.isAssignableFrom(commandMap.get(player.getName()).getClass())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6ageable mob&c!"));
			return null;
		}
		
		Ageable entity = (Ageable) commandMap.get(player.getName());
		
		if (parameters.containsKey("set") && !flags.contains("baby") && !flags.contains("adult"))
			if ((int) parameters.get("set") >= 0)
				entity.setAge((int) parameters.get("set"));
			else 
				entity.setAge(0);
		else if (!parameters.containsKey("set") && flags.contains("baby") && !flags.contains("adult"))
			entity.setBaby();
		else if (!parameters.containsKey("set") && !flags.contains("baby") && flags.contains("adult"))
			entity.setAdult();
		
		if (flags.contains("lock"))
			if (entity.getAgeLock())
				entity.setAgeLock(false);
			else
				entity.setAgeLock(true);
                return null;
	}
}
