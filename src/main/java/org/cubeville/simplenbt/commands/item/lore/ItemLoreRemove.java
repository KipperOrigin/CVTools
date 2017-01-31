package org.cubeville.simplenbt.commands.item.lore;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemLoreRemove extends Command {

	public ItemLoreRemove() {
		super("item lore remove");
        setPermission("snbt.item.lore");
		addBaseParameter(new CommandParameterInteger());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			throw new CommandExecutionException("&cItem must be held!");
		}
		
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		int i = (int) baseParameters.get(0);
		if (!(item.getLore().size() >= i) && !(i > 0)) {
			throw new CommandExecutionException("&cLine &6" + i + " &ais not available for this item!");
		}
		
		item.removeLore(i - 1);
		
		player.getInventory().setItemInMainHand(item.asItemStack());
		return new CommandResponse("&6Lore removed from line &6" + i);
	}

}
