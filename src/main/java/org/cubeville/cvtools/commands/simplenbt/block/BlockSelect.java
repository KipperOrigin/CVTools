package org.cubeville.cvtools.commands.simplenbt.block;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.utils.Colorize;

public class BlockSelect extends Command {

	public BlockSelect() {
		super("block select");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		if (!CommandMapManager.getBlockCommandMap().containsKey(player.getName())) {
			CommandMapManager.getBlockCommandMap().put(player.getName(), null);
		
			player.sendMessage(Colorize.addColor("&aYou may now select a &6block&a!"));
		}
	}

}
