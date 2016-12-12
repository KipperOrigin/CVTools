package org.cubeville.cvtools.commands.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.utils.BlockGetter;
import org.cubeville.commons.utils.Colorize;

public class CheckSign extends Command {

	public CheckSign() {
		super("checksign");
		addBaseParameter(new CommandParameterInteger());
		addBaseParameter(new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {

		List<Block> signs = BlockGetter.getBlocksInRadiusByType(player.getLocation(), (int) baseParameters.get(0), Material.SIGN, Material.WALL_SIGN);
		CharSequence cs = (String) baseParameters.get(1);
		int amount = 0;
		String color = "&c";

		for (Block sign: signs) {
			boolean contains = false;
			Sign signState = (Sign) sign.getState();
			String[] lines = signState.getLines();
			for (String line: lines) {
				if (line.contains(cs)) 
					contains = true;
			}
			if (contains)
				amount += 1;
		}
		
		if (amount > 0)
			color = "&a";
		
		player.sendMessage(Colorize.addColor(color + amount + " sign(s) contain the string &6" + (String) baseParameters.get(1)));
	}

}
