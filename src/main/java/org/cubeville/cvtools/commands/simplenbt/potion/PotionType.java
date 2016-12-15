package org.cubeville.cvtools.commands.simplenbt.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.PotionItem;

public class PotionType extends Command {

    public PotionType() {
        super("potion type");
        addBaseParameter(new CommandParameterString());
        // TODO Auto-generated constructor stub
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {

        if (player.getInventory().getItemInMainHand().getType() != Material.POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.LINGERING_POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.SPLASH_POTION 
            && player.getInventory().getItemInMainHand().getType() != Material.TIPPED_ARROW)
            return new CommandResponse("Not holding a potion!");
		
        PotionItem potionItem = new PotionItem(player.getInventory().getItemInMainHand());
        String type = (String) baseParameters.get(0);
		
        if (type.equalsIgnoreCase("lingering"))
            potionItem.setLingering();
        else if (type.equalsIgnoreCase("splash"))
            potionItem.setSplash();
        else if (type.equalsIgnoreCase("normal"))
            potionItem.setNormal();
        else if (type.equalsIgnoreCase("arrow"))
            potionItem.setTippedArrow();
        else {
            return new CommandResponse("Invalid Potion Type");
        }
		
        player.getInventory().setItemInMainHand(potionItem.asItemStack());

        return null;
    }

}
