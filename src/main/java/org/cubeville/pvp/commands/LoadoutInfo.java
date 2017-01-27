package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutInfo extends Command {

	public LoadoutInfo() {
		super("loadout info");
	    setPermission("pvp.loadout.commands");
		addBaseParameter(new CommandParameterString());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		if (CVTools.getInstance().getLoadoutManager().contains((String) baseParameters.get(0))) {
			LoadoutContainer lc = CVTools.getInstance().getLoadoutManager().getLoadoutByName((String) baseParameters.get(0));
			CommandResponse cr = new CommandResponse("&6&l&n----" + baseParameters.get(0).toString().toLowerCase() + "=====");
			cr.addMessage("&6---Sub-Loadouts");
			
			for (String loadoutName: lc.getInventoriesByName()) {
				cr.addMessage("&a-" + loadoutName);
			}
			
			cr.addMessage("---&6Tags");
			
			for (String tag: lc.getTags()) {
				cr.addMessage("&a-" + tag);
			}
			
			return cr;
		} else {
			throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0).toString().toLowerCase() + " &cdoes not exist!");
		}
	}
	
	

}
