package org.cubeville.simplenbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterShort;
import org.cubeville.commons.commands.CommandResponse;

public class ItemType extends Command {

    public ItemType() {
        super("item type");
        setPermission("snbt.item");
        addBaseParameter(new CommandParameterEnum(Material.class));
        addParameter("data", true, new CommandParameterShort());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
		
        if (item == null || item.getType() == Material.AIR)
            throw new CommandExecutionException("&cMust be holding an item!");
		
        item.setType((Material) baseParameters.get(0));
		
        if (parameters.containsKey("data")) {
            item.setDurability((short) parameters.get("data"));
        }

        return new CommandResponse("&aItem type successfully changed to &6" + ((Material) baseParameters.get(0)).name());
    }

}
