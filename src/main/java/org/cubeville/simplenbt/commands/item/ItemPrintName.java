package org.cubeville.simplenbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class ItemPrintName extends Command {

    public ItemPrintName() {
        super("item print name");
        setPermission("snbt.item");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
        
        if (item == null || item.getType() == Material.AIR) {
            throw new CommandExecutionException("&cMust be holding an item!");
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta.hasDisplayName()) {
            return new CommandResponse(meta.getDisplayName());
        } else {
            throw new CommandExecutionException("&cItem has no custom name!");
        }
    }
}
