package org.cubeville.cvtools.commands.simplenbt.block;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockDeselect extends Command {

	public BlockDeselect() {
		super("block deselect");
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (CommandMapManager.getBlockCommandMap().containsKey(player.getName())) {
			if (CommandMapManager.getBlockCommandMap().get(player.getName()) == null) {
				player.sendMessage(Colorize.addColor("&cSelection cancelled!"));
			} else
				player.sendMessage(Colorize.addColor("&cBlock deselected!"));
			
			CommandMapManager.getBlockCommandMap().remove(player.getName());
		} else {
			player.sendMessage(Colorize.addColor("&cCannot cancel invalid block selection!"));
		}
                return null;
	}

}
