package org.cubeville.cvtools.commands.simplenbt.item.attributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemAttributesRemove extends Command {

    public ItemAttributesRemove() {                                                                     
        super("item attributes remove");
        addBaseParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return;
		
		int i = (int) baseParameters.get(0);
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		
		nbtItem.removeAttribute(i);
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
	}
}
