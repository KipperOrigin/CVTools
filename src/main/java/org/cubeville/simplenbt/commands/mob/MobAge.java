package org.cubeville.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobAge extends Command {

	public MobAge() {
		super("mob age");
        setPermission("snbt.mob");
		addParameter("set", true, new CommandParameterInteger());
		addParameter("lock", true, new CommandParameterBoolean());
		addFlag("baby");
		addFlag("adult");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		} else if (!(commandMap.get(player) instanceof Tameable)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		}
		
		Ageable entity = (Ageable) commandMap.get(player);
		
		if (parameters.containsKey("set") && !flags.contains("baby") && !flags.contains("adult"))
			if ((int) parameters.get("set") >= 0)
				entity.setAge((int) parameters.get("set"));
			else 
				entity.setAge(0);
		else if (!parameters.containsKey("set") && flags.contains("baby") && !flags.contains("adult"))
			entity.setBaby();
		else if (!parameters.containsKey("set") && !flags.contains("baby") && flags.contains("adult"))
			entity.setAdult();
		
		if (parameters.containsKey("lock")) {
			entity.setAgeLock((boolean) parameters.get("lock"));
		}
		return new CommandResponse("&aAge for mob &6" + entity.getCustomName() + " &aset!");
	}
}
