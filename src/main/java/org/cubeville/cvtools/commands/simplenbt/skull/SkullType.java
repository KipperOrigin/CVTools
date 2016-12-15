package org.cubeville.cvtools.commands.simplenbt.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterShort;
import org.cubeville.commons.commands.CommandResponse;

public class SkullType extends Command {

	public SkullType() {
		super("skull type");
		addBaseParameter(new CommandParameterShort());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {

		ItemStack item = player.getInventory().getItemInMainHand();
		short data = (short) baseParameters.get(0);
		
		if (item.getType() != Material.SKULL_ITEM)
			return null;

		if (data < 0 || data > 5)
			return null;
		
		item.setDurability(data);
		
		player.getInventory().setItemInMainHand(item);

                return null;
	}

}
