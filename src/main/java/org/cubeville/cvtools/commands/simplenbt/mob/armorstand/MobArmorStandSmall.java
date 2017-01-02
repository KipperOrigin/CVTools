package org.cubeville.cvtools.commands.simplenbt.mob.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobArmorStandSmall extends Command {
	
	public MobArmorStandSmall() {
		super("armorstand small");
		addBaseParameter(new CommandParameterBoolean());
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select an &6armor stand&c!");
		} else if (!(commandMap.get(player) instanceof ArmorStand)) {
			throw new CommandExecutionException("&cPlease select an &6armor stand&c!");
		}
		
		ArmorStand stand = (ArmorStand) commandMap.get(player);
		
		stand.setSmall((boolean) baseParameters.get(0));
        return new CommandResponse("&aArmor Stand small set to &6" + Boolean.toString((boolean) baseParameters.get(0)));
	}
}
