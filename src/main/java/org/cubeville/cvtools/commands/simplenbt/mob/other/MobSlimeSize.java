package org.cubeville.cvtools.commands.simplenbt.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSlimeSize extends Command {

	public MobSlimeSize() {
		super("mob slime size");
		addBaseParameter(new CommandParameterInteger());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6slime &cor&6 magma cube&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Slime)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6slime &cor&6 magma cube&c!"));
			return null;
		}
		
		if ((int) baseParameters.get(0) > 32) {
			player.sendMessage(Colorize.addColor("&cPlease use a size smaller than &632&c!"));
			return null;
		} else
			((Slime) commandMap.get(player.getName())).setSize((int) baseParameters.get(0));
                return null;
	}
}

