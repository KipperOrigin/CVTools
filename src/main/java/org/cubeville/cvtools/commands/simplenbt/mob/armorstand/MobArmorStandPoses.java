package org.cubeville.cvtools.commands.simplenbt.mob.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEulerAngle;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.AdvancedSlots;
import org.cubeville.commons.utils.Colorize;

public class MobArmorStandPoses extends Command {
	
	public MobArmorStandPoses() {
		super("armorstand pose");
		addBaseParameter(new CommandParameterString());
		addBaseParameter(new CommandParameterEulerAngle());
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
		
		boolean set = AdvancedSlots.setAngleByName(stand, (String) baseParameters.get(0), (EulerAngle) baseParameters.get(1));
		
		if (set)
			player.sendMessage(Colorize.addColor("&aAngle Set to &6" + (String) baseParameters.get(0) + "&c!"));
		else
			player.sendMessage(Colorize.addColor("&6" + (String) baseParameters.get(0) + "&cis not a valid body part!"));
                return null;
	}
}
