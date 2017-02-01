package org.cubeville.simplenbt.commands.item.attributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemAttributesClear extends Command {

    public ItemAttributesClear() {                                                                     
        super("item attributes clear");
        setPermission("snbt.item.attributes");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}
		
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		
		nbtItem.clearAttributes();
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
        return new CommandResponse("&aAttributes cleared!");
	}
}
