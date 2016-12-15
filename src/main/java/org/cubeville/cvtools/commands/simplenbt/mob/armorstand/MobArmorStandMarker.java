package org.cubeville.cvtools.commands.simplenbt.mob.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;

public class MobArmorStandMarker extends Command {
	
	public MobArmorStandMarker() {
		super("armorstand marker");
		//addBaseParameter(new CommandParameterBoolean());;
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof ArmorStand)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return null;
		}
		
		ArmorStand stand = (ArmorStand) commandMap.get(player.getName());
		
		stand.setMarker((boolean) baseParameters.get(0));
                return null;
	}
}
