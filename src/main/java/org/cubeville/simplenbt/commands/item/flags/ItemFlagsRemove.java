package org.cubeville.simplenbt.commands.item.flags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnumeratedStringList;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemFlagsRemove extends Command {

	List<ItemFlag> flagList = new ArrayList<>();
	
	public ItemFlagsRemove() {
		super("item flags remove");
        setPermission("snbt.item.flags");
		Set<String> flags = new HashSet<String>(Arrays.asList("hide_attributes","hide_destroys","hide_potion_effects","hide_enchants","hide_placedon","hide_unbreakable"));
		addBaseParameter(new CommandParameterEnumeratedStringList(flags));
	}

	@Override
	@SuppressWarnings("unchecked")
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			throw new CommandExecutionException("&cItem must be held!");
		}
		
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		Set<String> flagsRemove = (Set<String>) baseParameters.get(0);
		
		if (flagsRemove != null) {
			for (String string: flagsRemove) { 
				flagList.add(ItemFlag.valueOf(string.toUpperCase()));
			}
		}
		
		ItemFlag[] finalFlags = flagList.toArray(new ItemFlag[0]);
		item.removeFlags(finalFlags);
		player.getInventory().setItemInMainHand(item.asItemStack());
		
		return new CommandResponse("&aFlags removed from item!");
	}

}

