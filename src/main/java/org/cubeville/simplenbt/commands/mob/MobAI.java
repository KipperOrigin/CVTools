package org.cubeville.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobAI extends Command {

	public MobAI() {
		super("mob ai");
        setPermission("snbt.mob");
		addBaseParameter(new CommandParameterBoolean());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		} else if (!(commandMap.get(player) instanceof LivingEntity)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		}
		
		((LivingEntity) commandMap.get(player)).setAI((Boolean) baseParameters.get(0));
		
		return new CommandResponse("&aMob AI set to &6" + Boolean.toString((boolean) baseParameters.get(0)));
	}

}
