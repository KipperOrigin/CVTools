package org.cubeville.cvtools.commands.simplenbt.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSelect extends Command {

	public MobSelect() {
		super("mob select");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (!CommandMapManager.getLivingEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getLivingEntityCommandMap().put(player.getName(), null);
		
		if (!CommandMapManager.getEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getEntityCommandMap().put(player.getName(), null);
		
		player.sendMessage(Colorize.addColor("&aYou may now select a &6mob&a!"));

                return null;
        }

}
