package org.cubeville.simplenbt.commands.selection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMapManager;

public class ObjectDeselect extends Command {

	public ObjectDeselect() {
		super("deselect");
        setPermission("snbt.selection");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		if (CommandMapManager.primaryMap.contains(player)) {
			CommandResponse cr = new CommandResponse();
			if (CommandMapManager.primaryMap.get(player) == null) {
				cr.addMessage("&aYou are no longer selecting an &6Object&a!");
			} else {
				cr.addMessage("&6" + CommandMapManager.primaryMap.get(player).toString() + " &adeselected!");
			}
			CommandMapManager.primaryMap.removePlayer(player);
			return cr;
		} else {
			throw new CommandExecutionException("&cYou are not selecting an &6Object&c!");
		}
	}

}

