package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSnowmanDerp extends Command {

	public MobSnowmanDerp() {
		super("snowman derp");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterBoolean("derp","underp"));
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6snowman&c!");
		} else if (!(commandMap.get(player) instanceof Snowman)) {
			throw new CommandExecutionException("&cPlease select a &6snowman&c!");
		}
		
		Snowman snowman = (Snowman) commandMap.get(player);
		
		snowman.setDerp((boolean) baseParameters.get(0));
		return new CommandResponse("&aSnowman derp set to &6" + Boolean.toString((boolean) baseParameters.get(0)));
                		
	}
}
