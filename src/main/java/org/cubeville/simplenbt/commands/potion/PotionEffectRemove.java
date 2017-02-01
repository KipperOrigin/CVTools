package org.cubeville.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.PotionItem;

public class PotionEffectRemove extends Command {

    public PotionEffectRemove() {
        super("potion remove");
        setPermission("snbt.potion");
        addBaseParameter(new CommandParameterInteger());
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
		
        if (!(potionItem.getRawMeta().getCustomEffects().size() >= (int) baseParameters.get(0)) || (int) baseParameters.get(0) <= 0) {
            throw new CommandExecutionException("&cThe line &6" + baseParameters.get(0) + " &cdoes not exist for this potion.");
        }
		
        String type = potionItem.getPotionEffectType((int) baseParameters.get(0) - 1).getName().toLowerCase();
		
        potionItem.removeEffect((int) baseParameters.get(0) - 1);
        player.getInventory().setItemInMainHand(potionItem.asItemStack());

        return new CommandResponse("&aPotion Effect Type &6" + type + " &afrom line &6" + baseParameters.get(0) + " &aremoved from potion.");
    }
}
