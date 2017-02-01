package org.cubeville.simplenbt.commands.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobHorseVariant extends Command {

	public MobHorseVariant() {
		super("horse variant");
        setPermission("snbt.horse");
		addBaseParameter(new CommandParameterEnum(Variant.class));
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
		
		horse.setVariant((Variant) baseParameters.get(0));
		return new CommandResponse("&cHorse variant changed to &6" + ((Variant) baseParameters.get(0)).name());
	}

}
