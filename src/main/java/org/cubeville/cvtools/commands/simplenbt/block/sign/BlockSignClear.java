package org.cubeville.cvtools.commands.simplenbt.block.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockSignClear extends Command {

	public BlockSignClear() {
		super("block sign clear");
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Sign)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		}
		
		Sign sign = (Sign) commandMap.get(player.getName());
		sign.setLine(0, "");
		sign.setLine(1, "");
		sign.setLine(2, "");
		sign.setLine(3, "");
		sign.update();
	}
}
