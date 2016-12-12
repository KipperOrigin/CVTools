package org.cubeville.cvtools.commands.simplenbt.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Rabbit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit.Type;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobRabbitType extends Command {

	public MobRabbitType() {
		super("mob rabbit type");
		addBaseParameter(new CommandParameterEnum(Type.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6rabbit&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Rabbit)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6rabbit&c!"));
			return;
		}
		
		((Rabbit) commandMap.get(player.getName())).setRabbitType((Type) baseParameters.get(0));
	}
}
