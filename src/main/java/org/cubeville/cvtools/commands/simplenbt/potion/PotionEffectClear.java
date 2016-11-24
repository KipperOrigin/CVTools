package org.cubeville.cvtools.commands.simplenbt.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.cvtools.nbt.PotionItem;

public class PotionEffectClear extends Command {

	public PotionEffectClear() {
		super("potion clear");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {

		if (player.getInventory().getItemInMainHand().getType() != Material.POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.LINGERING_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.SPLASH_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.TIPPED_ARROW)
			return;
		
		PotionItem potionItem = new PotionItem(player.getInventory().getItemInMainHand());
		
		potionItem.clearEffects();
		
		player.getInventory().setItemInMainHand(potionItem.asItemStack());
	}

}