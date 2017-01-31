package org.cubeville.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.PotionItem;

public class PotionEffectClear extends Command {

    public PotionEffectClear() {
        super("potion clear");
        setPermission("snbt.potion");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {

        if (player.getInventory().getItemInMainHand().getType() != Material.POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.LINGERING_POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.SPLASH_POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.TIPPED_ARROW) {
            throw new CommandExecutionException("&cHeld item must be a &6Potion&c!");
        }
		
        PotionItem potionItem = new PotionItem(player.getInventory().getItemInMainHand());
		
        potionItem.clearEffects();
        player.getInventory().setItemInMainHand(potionItem.asItemStack());

        return new CommandResponse("&aPotion effects cleared from potion.");
    }

}
