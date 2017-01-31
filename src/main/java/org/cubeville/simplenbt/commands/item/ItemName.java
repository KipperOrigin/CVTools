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
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

public class ItemName extends Command {

    public ItemName() {
        super("item name");
        setPermission("snbt.item");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
		
        if (item == null || item.getType() == Material.AIR) {
            throw new CommandExecutionException("&cMust be holding an item!");
        }
		
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.addColor((String) baseParameters.get(0)));
        item.setItemMeta(meta);
		
        return new CommandResponse("&aItem name set to &r" + baseParameters.get(0));
    }

}
