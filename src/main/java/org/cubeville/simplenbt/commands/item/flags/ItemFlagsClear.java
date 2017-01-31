package org.cubeville.simplenbt.commands.item.flags;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemFlagsClear extends Command {

	public ItemFlagsClear() {
		super("item flags clear");
        setPermission("snbt.item.flags");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand() == null && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			throw new CommandExecutionException("&cItem must be held!");
		}
		
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		item.clearFlags();
		
		return new CommandResponse("&aItem flags cleared!");
	}

}
