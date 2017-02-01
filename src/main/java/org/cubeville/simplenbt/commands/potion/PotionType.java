package org.cubeville.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.nbt.PotionItem;

public class PotionType extends Command {

    public PotionType() {
        super("potion type");
        setPermission("snbt.potion");
        addBaseParameter(new CommandParameterString());
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
        String originalType = potionItem.asItemStack().getType().name().toLowerCase();
        String type = (String) baseParameters.get(0);
		
        if (type.equalsIgnoreCase("lingering")) {
            potionItem.setLingering();
        } else if (type.equalsIgnoreCase("splash")) {
            potionItem.setSplash();
    	} else if (type.equalsIgnoreCase("normal")) {
            potionItem.setNormal();
		} else if (type.equalsIgnoreCase("arrow")) {
            potionItem.setTippedArrow();
		} else {
			player.sendMessage(ColorUtils.addColor("&cParameter must be a valid &6Potion Item&c!"));
           	throw new CommandExecutionException("&cValid types are &6lingering&c, &6splash&c, &6normal&c, &6arrow");
        }
		
        player.getInventory().setItemInMainHand(potionItem.asItemStack());

        return new CommandResponse("&aPotion Item changed from &6" + originalType + "&a to &6" + type.toLowerCase());
    }

}
