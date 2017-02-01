package org.cubeville.simplenbt.commands.block.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ObjectUtils;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockSignClear extends Command {

	public BlockSignClear() {
		super("block sign clear");
		setPermission("snbt.sign");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		Block block;
		
		try {
			block = ObjectUtils.getObjectAsBlock(commandMap.get(player));
		} catch (RuntimeException e) {
			throw new CommandExecutionException("&cPlease select a sign!");
		}
		
		if (!(block.getState() instanceof Sign)) {
			throw new CommandExecutionException("&cPlease select a sign!");
		}
		
		Sign sign = (Sign) block.getState();
		sign.setLine(0, "");
		sign.setLine(1, "");
		sign.setLine(2, "");
		sign.setLine(3, "");
		sign.update();
                return null;
	}
}
