package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobRabbitType extends Command {

	public MobRabbitType() {
		super("rabbit type");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterEnum(Type.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6rabbit&c!");
		} else if (!(commandMap.get(player) instanceof Rabbit)) {
			throw new CommandExecutionException("&cPlease select a &6rabbit&c!");
		}
		
		((Rabbit) commandMap.get(player)).setRabbitType((Type) baseParameters.get(0));
        return new CommandResponse("&aRabbit type changed to &6" + ((Type) baseParameters.get(0)).name());
	}
}
