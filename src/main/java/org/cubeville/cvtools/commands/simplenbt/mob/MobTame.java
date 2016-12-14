package org.cubeville.cvtools.commands.simplenbt.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobTame extends Command {

	public MobTame() {
		super("mob tame");
		addBaseParameter(new CommandParameterBoolean());
		addParameter("player", true, new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6tameable mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !Tameable.class.isAssignableFrom(commandMap.get(player.getName()).getClass())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6tameable mob&c!"));
			return;
		}
		
		Tameable entity = (Tameable) commandMap.get(player.getName());
		Player playerT = player;
		Boolean tame = (Boolean) baseParameters.get(0);

		if (parameters.containsKey("player"))
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer((String) parameters.get("player"))))
				playerT = Bukkit.getPlayer((String) parameters.get("player"));
			else
				return;
		
		if (tame) {
			entity.setOwner(playerT);
		} else {
			entity.setOwner(null);
		}
		
		entity.setTamed(tame);
	}

}
