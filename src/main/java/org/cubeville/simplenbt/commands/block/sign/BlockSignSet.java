package org.cubeville.simplenbt.commands.block.sign;

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
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.commons.utils.ObjectUtils;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockSignSet extends Command {

	public BlockSignSet() {
		super("block sign set");
		setPermission("snbt.sign");
		addBaseParameter(new CommandParameterInteger());
		addBaseParameter(new CommandParameterString());
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
		
		if ((int) baseParameters.get(0) > 4 || (int) baseParameters.get(0) < 1) {
			throw new CommandExecutionException("&cInvalid sign line!");
		}
		
		Sign sign = (Sign) block.getState();
		sign.setLine((int) baseParameters.get(0) - 1, ColorUtils.addColor((String) "&0" + baseParameters.get(1)));
		sign.update();
		return new CommandResponse("&aLine &6" + baseParameters.get(0) + " &a set to &6" + baseParameters.get(1) + " &aon sign!");
	}
}
