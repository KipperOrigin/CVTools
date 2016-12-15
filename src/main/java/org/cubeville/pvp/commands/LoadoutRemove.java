package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.CVTools;
import org.cubeville.pvp.loadout.LoadoutContainer;

public class LoadoutRemove extends Command{

	public LoadoutRemove(){
		super("loadout remove");
		addBaseParameter(new CommandParameterString());
		addParameter("team", true, new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		if (!CVTools.getInstance().getLoadoutManager().contains((String) baseParameters.get(0)))
			throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0).toString().toLowerCase() + "&c does not exist!");
		
		if (parameters.containsKey("team")) {
			LoadoutContainer lc = CVTools.getInstance().getLoadoutManager().getLoadout((String) baseParameters.get(0));
			
			if (!lc.containsInventory((String) parameters.get("team")))
				throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0).toString().toLowerCase() + "&c does not contain sub loadout &6" + parameters.get("team"));
			
			lc.removeInventory((String) parameters.get("team"));
			player.sendMessage(Colorize.addColor("&aSuccessfully removed sub loadout &6" + parameters.get("team") + "&a from loadout &6" + baseParameters.get(0).toString().toLowerCase()));
		
		} else {
			CVTools.getInstance().getLoadoutManager().removeLoadout((String) baseParameters.get(0));
			player.sendMessage(Colorize.addColor("&aSuccessfully removed loadout &6" + baseParameters.get(0).toString().toLowerCase()));
		}
	}
}
