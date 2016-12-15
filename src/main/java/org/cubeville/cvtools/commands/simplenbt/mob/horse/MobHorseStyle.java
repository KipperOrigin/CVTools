package org.cubeville.cvtools.commands.simplenbt.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobHorseStyle extends Command {

	public MobHorseStyle() {
		super("mob horse style");
		addBaseParameter(new CommandParameterEnum(Style.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Horse)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return null;
		}
		
		Horse horse = (Horse) commandMap.get(player.getName());
		
		if (horse.getVariant() != Variant.HORSE) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return null;
		}
		
		horse.setStyle((Style) baseParameters.get(0));
                return null;
	}
}
