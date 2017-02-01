package org.cubeville.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterPotionEffectType;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.PotionItem;
import org.cubeville.cvtools.nbt.PotionItem.NBTPotionEffect;

public class PotionEffectAdd extends Command {

	public PotionEffectAdd() {
		super("potion add");
        setPermission("snbt.potion");
		addBaseParameter(new CommandParameterPotionEffectType());
		addParameter("level", true, new CommandParameterInteger());
		addParameter("duration", true, new CommandParameterInteger());
		// TODO Auto-generated constructor stub
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
		PotionEffectType type = (PotionEffectType) baseParameters.get(0);
		NBTPotionEffect potionEffect = new NBTPotionEffect();
		int dur = 100;
		int level = 0;
		
		if (parameters.containsKey("duration"))
			dur = (int) parameters.get("duration") * 20;
		
		if (parameters.containsKey("level"))
			level = (int) parameters.get("level") - 1;
		
		
		if (player.getInventory().getItemInMainHand().getType() == Material.LINGERING_POTION)
			dur *= 4;
		else if (player.getInventory().getItemInMainHand().getType() == Material.TIPPED_ARROW)
			dur *= 8;
		
		potionEffect.creatPotionEffect(type, dur, level);
		potionItem.addEffect(potionEffect.getPotionEffect());
		player.getInventory().setItemInMainHand(potionItem.asItemStack());

		return new CommandResponse("&aPotion Effect Type &6" + type.getName().toLowerCase() + " &awith level:&6" + (level + 1) + "&aand&6 duration:&6" + dur + " &aadded to potion.");
	}

}
