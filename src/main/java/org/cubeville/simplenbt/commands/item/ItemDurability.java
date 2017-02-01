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
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterShort;
import org.cubeville.commons.commands.CommandResponse;

public class ItemDurability extends Command {

    public ItemDurability() {
        super("item durability");
        setPermission("snbt.item");
        addParameter("set", true, new CommandParameterShort());
        addParameter("unbreakable", true, new CommandParameterBoolean());
        addFlag("max");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
		
        if (item == null || item.getType() == Material.AIR) {
            throw new CommandExecutionException("&cMust be holding an item!");
        }
		
        if (parameters.containsKey("set") && !flags.contains("max")) {
            item.setDurability((short) (item.getType().getMaxDurability() - ((short) parameters.get("set"))));
        } else if (!parameters.containsKey("set") && flags.contains("max")) {
            item.setDurability((short) 0);
        }
		

        if (parameters.containsKey("unbreakable")) {
            ItemMeta meta = item.getItemMeta();
            meta.spigot().setUnbreakable((boolean) parameters.get("unbreakable"));
            item.setItemMeta(meta);
        }
        return new CommandResponse("&aItem durability successfully changed!");
		
    }

}
