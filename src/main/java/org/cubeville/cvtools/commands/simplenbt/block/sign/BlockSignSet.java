package org.cubeville.cvtools.commands.simplenbt.block.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockSignSet extends Command {

	public BlockSignSet() {
		super("block sign set");
		addBaseParameter(new CommandParameterInteger());
		addBaseParameter(new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (!(commandMap.get(player.getName()).getState() instanceof Sign)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		}
		
		if ((int) baseParameters.get(0) > 4 || (int) baseParameters.get(0) < 1) {
 			player.sendMessage(Colorize.addColor("&cInvalid sign line!"));
			return;
		}
		
		Block block = commandMap.get(player.getName());
		Sign sign = (Sign) block.getState();
		sign.setLine((int) baseParameters.get(0) - 1, Colorize.addColor((String) "&0" + baseParameters.get(1)));
		sign.update();
	}
}
