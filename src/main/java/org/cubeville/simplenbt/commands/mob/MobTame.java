package org.cubeville.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobTame extends Command {

	public MobTame() {
		super("mob tame");
        setPermission("snbt.mob");
		addBaseParameter(new CommandParameterBoolean());
		addParameter("player", true, new CommandParameterOnlinePlayer());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		} else if (!(commandMap.get(player) instanceof Tameable)) {
			throw new CommandExecutionException("&cPlease select a &6tameable mob&c!");
		}
		
		Tameable entity = (Tameable) commandMap.get(player);
		Player playerT = player;
		Boolean tame = (Boolean) baseParameters.get(0);

		if (parameters.containsKey("player")) {
			playerT = (Player) parameters.get("player");
		}
		
		if (tame) {
			entity.setOwner(playerT);
		} else {
			entity.setOwner(null);
		}
		
		entity.setTamed(tame);
        return new CommandResponse("&aMob &6" + ((Entity) entity).getCustomName() + " &atamed to &6" + playerT.getName());
	}

}
