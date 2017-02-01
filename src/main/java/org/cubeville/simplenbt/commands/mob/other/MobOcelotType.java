package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Ocelot.Type;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobOcelotType extends Command {

	public MobOcelotType() {
		super("ocelot type");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterEnum(Type.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6ocelot&c!");
		} else if (!(commandMap.get(player) instanceof Ocelot)) {
			throw new CommandExecutionException("&cPlease select a &6ocelot&c!");
		}
		
		((Ocelot) commandMap.get(player)).setCatType((Type) baseParameters.get(0));
        return new CommandResponse("&aOcelot type changed to &6" + (Type) baseParameters.get(0));
	}
}
