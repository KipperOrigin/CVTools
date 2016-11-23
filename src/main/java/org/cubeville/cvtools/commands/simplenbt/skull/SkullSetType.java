package org.cubeville.cvtools.commands.simplenbt.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterShort;

public class SkullSetType extends Command {

	public SkullSetType() {
		super("skull type");
		addBaseParameter(new CommandParameterShort());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {

		ItemStack item = player.getInventory().getItemInMainHand();
		short data = (short) baseParameters.get(0);
		
		if (item.getType() != Material.SKULL_ITEM)
			return;

		if (data < 0 || data > 5)
			return;
		
		item.setDurability(data);
		
		player.getInventory().setItemInMainHand(item);
	}

}
