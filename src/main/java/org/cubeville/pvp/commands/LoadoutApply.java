package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandParameterString;
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
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		String team = "main";
		Player playerInv = player;
		LoadoutContainer loadout = CVTools.getInstance().getLoadoutManager().getLoadout((String) baseParameters.get(0));
		
		if (loadout == null)
			return;
		if (parameters.containsKey("team"))
			team = (String) parameters.get("team");
		if (parameters.containsKey("player"))
			playerInv = (Player) parameters.get("player");
		
		LoadoutHandler.applyLoadoutToPlayer(playerInv, loadout, team);
	}

}
