package org.cubeville.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobUntame extends Command {

	public MobUntame() {
		super("mob untame");
        setPermission("snbt.mob");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		} else if (!(commandMap.get(player) instanceof Tameable)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		}
		
		Tameable entity = (Tameable) commandMap.get(player);
		entity.setOwner(null);
		entity.setTamed(false);
		return new CommandResponse("&aMob untamed!");
	}

}
