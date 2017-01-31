package org.cubeville.simplenbt.commands.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobHorseColor extends Command {

	public MobHorseColor() {
		super("horse color");
        setPermission("snbt.horse");
		addBaseParameter(new CommandParameterEnum(Color.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6horse&c!");
		} else if (!(commandMap.get(player) instanceof Horse)) {
			throw new CommandExecutionException("&cPlease select a &6horse&c!");
		}
		
		Horse horse = (Horse) commandMap.get(player);
		
		if (horse.getVariant() != Variant.HORSE) {
			throw new CommandExecutionException("&cPlease select a &6normal horse&c!");
		}
		
		horse.setColor((Color) baseParameters.get(0));
        return new CommandResponse("&aHorse color changed to" + ((Color) baseParameters.get(0)).name());
	}
}
