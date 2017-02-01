package org.cubeville.pvp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterListString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.CommandParameterLoadout;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutList extends Command{

	private CommandResponse cr;
	
	public LoadoutList(){
		super("loadout list");
	    setPermission("pvp.loadout.commands");
		addParameter("sub", true, new CommandParameterLoadout());
		addParameter("tags", true, new CommandParameterListString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		List<String> loadouts = CVTools.getInstance().getLoadoutManager().getLoadoutNames();
		cr = new CommandResponse("&6========================&aLoadouts&6========================");
		
		if (parameters.containsKey("sub") && !parameters.containsKey("tags")) {
			cr.setBaseMessage("&6===================&aLoadout " + ((LoadoutContainer) parameters.get("sub")).getName() + "&6===================");
			loadouts = ((LoadoutContainer) parameters.get("sub")).getInventoriesByName();
		} else if (!parameters.containsKey("sub") && parameters.containsKey("tags")) {
			String tagsString = "";
			
			for(String tag: (List<String>) parameters.get("tags")) {
				tagsString = tagsString + "&a" + tag.toLowerCase() + "&c,";
			}
			
			tagsString = tagsString.substring(0, tagsString.length() - 1);
			
			cr.setBaseMessage("&6=====&aTags " + tagsString + "&6=====");
			loadouts = CVTools.getInstance().getLoadoutManager().getLoadoutNamesByTags((List<String>) parameters.get("tags"));
		}
		
		String message = "";
		for (String mloadout: loadouts) {
			message += "&f" + mloadout + "&c||";
		}
		if (message.length() > 2) message = message.substring(0, message.length() - 2);
		cr.addMessage(message);
		
        return cr;
	}
	
	public void createMessage(List<String> loadouts) {
		List<String> loadoutList = new ArrayList<>();
		int i = 0;
		
		for (String loadoutString: loadouts) {
			String newLoadout;
			if (loadoutList.size() < i+1) {
				newLoadout = "";
				loadoutList.add(i, newLoadout);
			} else {
				newLoadout = loadoutList.get(i);
			}
			System.out.println((newLoadout + loadoutString + "||").length());
			if ((newLoadout + loadoutString + "||").length() > 100) {
				newLoadout = newLoadout.substring(0, newLoadout.length() - 2);
				i++;
			} else {
				newLoadout = newLoadout + "&f" + loadoutString + "&c||";
			}
			loadoutList.set(i, newLoadout);
		}
		
		loadoutList.set(i, loadoutList.get(i).substring(0, loadoutList.get(i).length() - 2));
		
		for (String message: loadoutList) {
			cr.addMessage(message);
		}
	}

}

