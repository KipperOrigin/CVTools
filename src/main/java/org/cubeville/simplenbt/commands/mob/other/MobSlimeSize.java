package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSlimeSize extends Command {

	public MobSlimeSize() {
		super("slime size");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterInteger());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6slime &cor&6 magma cube&c!");
		} else if (!(commandMap.get(player) instanceof Slime)) {
			throw new CommandExecutionException("&cPlease select a &6slime &cor&6 magma cube&c!");
		}
		
		if ((int) baseParameters.get(0) > 32) {
			throw new CommandExecutionException("&cPlease use a size smaller than &632&c!");
		} else {
			((Slime) commandMap.get(player)).setSize((int) baseParameters.get(0));
			return new CommandResponse("&aSlime size changed to &6" + baseParameters.get(0));
		}
	}
}

