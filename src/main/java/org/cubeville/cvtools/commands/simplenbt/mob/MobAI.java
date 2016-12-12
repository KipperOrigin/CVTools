package org.cubeville.cvtools.commands.simplenbt.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobAI extends Command {

	public MobAI() {
		super("mob ai");
		addBaseParameter(new CommandParameterBoolean());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		}
		
		commandMap.get(player.getName()).setAI((Boolean) baseParameters.get(0));
		
		player.sendMessage(baseParameters.get(0).toString());
		
		//if (commandMap.get(player.getName()).hasAI())
			//commandMap.get(player.getName()).setAI(false);
		//else
			//commandMap.get(player.getName()).setAI(true);
	}

}
