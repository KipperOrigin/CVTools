package org.cubeville.simplenbt.commands.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class SkullOwner extends Command {

    public SkullOwner() {
        super("skull owner");
        setPermission("snbt.skull");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();
        SkullMeta meta;

        if (item.getType() != Material.SKULL_ITEM)
            throw new CommandExecutionException("&cHeld item must be a &6Skull&c!");

        meta = (SkullMeta) player.getInventory().getItemInMainHand().getItemMeta();
        meta.setOwner((String) baseParameters.get(0));

        item.setDurability((short) 3);
        item.setItemMeta(meta);
        player.getInventory().setItemInMainHand(item);
		
        return new CommandResponse("&aSkull owner changed to &6" + baseParameters.get(0));
    }
}



