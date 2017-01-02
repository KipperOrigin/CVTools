package org.cubeville.cvtools.commands.simplenbt.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobCreeperCharge extends Command {

	public MobCreeperCharge() {
		super("mob creeper charge");
		addBaseParameter(new CommandParameterBoolean());
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6creeper&c!");
		} else if (!(commandMap.get(player) instanceof Creeper)) {
			throw new CommandExecutionException("&cPlease select a &6creeper&c!");
		}
		
		Creeper creeper = (Creeper) commandMap.get(player);
		
		creeper.setPowered((boolean) baseParameters.get(0));
		return new CommandResponse("&aCreeper charge set to &6" + Boolean.toString((boolean) baseParameters.get(0)));

	}
}
