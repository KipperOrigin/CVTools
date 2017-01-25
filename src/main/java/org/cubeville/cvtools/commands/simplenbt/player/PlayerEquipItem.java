package org.cubeville.cvtools.commands.simplenbt.player;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class PlayerEquipItem extends Command {

	public PlayerEquipItem() {
		super("player equip");
		addBaseParameter(new CommandParameterString());
		addParameter("player", true, new CommandParameterOnlinePlayer());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		String slot = (String) baseParameters.get(0);
		Player playerI = player;
		
		if (parameters.containsKey("player")) {
			playerI = (Player) parameters.get("player");
		}
		
		ItemStack inhand = player.getInventory().getItemInMainHand();
		
		if (inhand == null || inhand.getType() == Material.AIR) {
			throw new CommandExecutionException("&cYou must be holding an item!");
		}
		
		ItemStack equipped = new ItemStack(Material.AIR);
		
		if (slot.equalsIgnoreCase("head")) {
			if (playerI.getInventory().getHelmet() != null)
				equipped = playerI.getInventory().getHelmet();
			playerI.getInventory().setHelmet(inhand);
		} else if (slot.equalsIgnoreCase("chest")) {
			if (playerI.getInventory().getChestplate() != null)
				equipped = playerI.getInventory().getChestplate();
			playerI.getInventory().setChestplate(inhand);
		} else if (slot.equalsIgnoreCase("legs")) {
			if (playerI.getInventory().getLeggings() != null)
				equipped = playerI.getInventory().getLeggings();
			playerI.getInventory().setLeggings(inhand);
		} else if (slot.equalsIgnoreCase("boots")) {
			if (playerI.getInventory().getBoots() != null)
				equipped = playerI.getInventory().getBoots();
			playerI.getInventory().setBoots(inhand);
		} else {
			throw new CommandExecutionException("&cSlot must be &fhead&c,&fchest&c,&flegs&c,&fboots");
		}
		
		if (playerI.getInventory().getItemInMainHand() == null || playerI.getInventory().getItemInMainHand().getType() == Material.AIR) {
			playerI.getInventory().setItemInMainHand(equipped);
		} else {
			playerI.getInventory().setItem(playerI.getInventory().firstEmpty(), equipped);
		}
		if (inhand.getItemMeta().getDisplayName() != null) return new CommandResponse("&6" + inhand.getItemMeta().getDisplayName() + " &aapplied to player &6" + playerI.getName());
		else return new CommandResponse("&6" + inhand.getType().name() + " &aapplied to player &6" + playerI.getName());
	}

}
