package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutHandler;

public class LoadoutApply extends Command {

	public LoadoutApply() {
		super("loadout apply");
		addBaseParameter(new CommandParameterString());
		addParameter("team", true, new CommandParameterString());
		addParameter("player", true, new CommandParameterOnlinePlayer());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		String team = "main";
		Player playerInv = player;
		LoadoutContainer loadout = CVTools.getInstance().getLoadoutManager().getLoadout((String) baseParameters.get(0));
		
		if (loadout == null)
			throw new CommandExecutionException("&cLoadout &6" + baseParameters.get(0) + "&c does not exist!");
		if (parameters.containsKey("team"))
			team = (String) parameters.get("team");
		if (parameters.containsKey("player"))
			playerInv = (Player) parameters.get("player");
		
		if (!LoadoutHandler.applyLoadoutToPlayer(playerInv, loadout, team)) {
			throw new CommandExecutionException("&cSub Loadout &6" + parameters.get("team") + "&c does not exist for &6" + baseParameters.get(0));
		}
		return new CommandResponse("&aLoadout &6" + loadout.getInventory(team).getName() + "&a applied to &6" + playerInv.getName());
	}

}
