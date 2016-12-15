package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.CVTools;

public class LoadoutList extends Command{

	public LoadoutList(){
		super("loadout list");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		List<String> loadouts = CVTools.getInstance().getLoadoutManager().getLoadoutNames();
		String[] messages = {""};
		
		if (loadouts.isEmpty()) {
			throw new CommandExecutionException("&cLoadouts are empty!");
		}
		
		for (String loadout: loadouts) {
			int i = 0;
			
			if (messages[i] == null) {
				messages[i] = "";
			}
			if ((messages [i] + loadout + "&c||&f").length() > 100) {
				messages[i].substring(0, messages[i].length() - 2);
				i++;
			}
			else {
				messages[i] =  messages [i] + loadout + "&c||&f";
			}
		}
		
		player.sendMessage(Colorize.addColor("&6========================Loadouts========================"));
		for (String message: messages) {
			player.sendMessage(Colorize.addColor(message));
		}
                return null;
	}

}

