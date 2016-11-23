package org.cubeville.cvtools.commands.simplenbt.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterString;

public class SkullSetOwner extends Command {

	public SkullSetOwner() {
		super("skull owner");
		addBaseParameter(new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		SkullMeta meta;

		if (item.getType() != Material.SKULL_ITEM)
			return;

		meta = (SkullMeta) player.getInventory().getItemInMainHand().getItemMeta();
		meta.setOwner((String) baseParameters.get(0));

		item.setDurability((short) 3);
		item.setItemMeta(meta);
		player.getInventory().setItemInMainHand(item);
	}
}



