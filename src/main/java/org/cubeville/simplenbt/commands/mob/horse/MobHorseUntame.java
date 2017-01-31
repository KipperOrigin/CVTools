package org.cubeville.simplenbt.commands.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.nbt.NBTHorse;

public class MobHorseUntame extends Command {

	public MobHorseUntame() {
		super("horse untame");
        setPermission("snbt.horse");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6horse&c!");
		} else if (!(commandMap.get(player) instanceof Horse)) {
			throw new CommandExecutionException("&cPlease select a &6horse&c!");
		}
		
		NBTHorse horse = new NBTHorse((Horse) commandMap.get(player));
		horse.unTame();
		return new CommandResponse("&cHorse untamed!");
	}
	
}
