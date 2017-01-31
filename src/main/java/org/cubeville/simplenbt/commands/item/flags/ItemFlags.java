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

public class ItemFlags  extends Command {

    List<ItemFlag> addItemFlags = new ArrayList<>();
    List<ItemFlag> removeItemFlags = new ArrayList<>();
    ItemFlag itemFlag = null;
	
    public ItemFlags() {                                                                     
        super("item flags");
        setPermission("snbt.item.flags");
        Set<String> flags = new HashSet<String>(Arrays.asList("hide_attributes","hide_destroys","hide_potion_effects","hide_enchants","hide_placedon","hide_unbreakable"));
        addFlag("clearall");
        addFlag("hideall");
        addParameter("add", true, new CommandParameterEnumeratedStringList(flags));
        addParameter("remove", true, new CommandParameterEnumeratedStringList(flags));
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            throw new CommandExecutionException("&cItem must be held!");
        }
		
        NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
        Set<String> removeArgs = (Set<String>) parameters.get("remove");
        Set<String> addArgs = (Set<String>) parameters.get("add");
		
        if (flags.contains("clearall")) {
            item.clearFlags();
        }
		
        if (flags.contains("hideall")) {
            item.addAllFlags();
        }
		
        if (parameters.get("add") != null) {
            if (addArgs != null)
                for (String string: addArgs) { 
                    addItemFlags.add(ItemFlag.valueOf(string.toUpperCase()));
                }
			
            ItemFlag[] finalFlags = addItemFlags.toArray(new ItemFlag[0]);
            item.addFlags(finalFlags);
        }
		
        if (parameters.get("remove") != null) {
            if (removeArgs != null)
                for (String string: removeArgs) {
                    removeItemFlags.add(ItemFlag.valueOf(string.toUpperCase()));
                }
			
            ItemFlag[] finalFlags = (ItemFlag[]) removeItemFlags.toArray();
            item.removeFlags(finalFlags);
        }
		
        player.getInventory().setItemInMainHand(item.asItemStack());
		
        return new CommandResponse("&aItem flags successfully edited");
    }
}
