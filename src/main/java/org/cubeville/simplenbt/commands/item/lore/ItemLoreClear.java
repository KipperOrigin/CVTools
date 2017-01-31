package org.cubeville.simplenbt.commands.item.lore;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemLoreClear extends Command {

	public ItemLoreClear() {
		super("item lore clear");
        setPermission("snbt.item.lore");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}
		
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		item.clearLore();
		
		player.getInventory().setItemInMainHand(item.asItemStack());
		return new CommandResponse("&aItem lore cleared!");
	}
}

