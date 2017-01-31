package org.cubeville.simplenbt.commands.item.enchantments;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class ItemEnchantmentsClear extends Command {

	public ItemEnchantmentsClear() {                                                                     
        super("item enchant clear");
        setPermission("snbt.item.enchantments");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}
			
		clearEnchantments(item);
		return new CommandResponse("&aEnchantments cleared!");
	}
	
	public void clearEnchantments(ItemStack item) {
		item.removeEnchantment(Enchantment.ARROW_DAMAGE);
		item.removeEnchantment(Enchantment.ARROW_FIRE);
		item.removeEnchantment(Enchantment.ARROW_INFINITE);
		item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
		item.removeEnchantment(Enchantment.DAMAGE_ALL);
		item.removeEnchantment(Enchantment.DAMAGE_ARTHROPODS);
		item.removeEnchantment(Enchantment.DAMAGE_UNDEAD);
		item.removeEnchantment(Enchantment.DEPTH_STRIDER);
		item.removeEnchantment(Enchantment.DIG_SPEED);
		item.removeEnchantment(Enchantment.DURABILITY);
		item.removeEnchantment(Enchantment.FIRE_ASPECT);
		item.removeEnchantment(Enchantment.FROST_WALKER);
		item.removeEnchantment(Enchantment.KNOCKBACK);
		item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
		item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);
		item.removeEnchantment(Enchantment.LUCK);
		item.removeEnchantment(Enchantment.LURE);
		item.removeEnchantment(Enchantment.MENDING);
		item.removeEnchantment(Enchantment.OXYGEN);
		item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
		item.removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
		item.removeEnchantment(Enchantment.PROTECTION_FALL);
		item.removeEnchantment(Enchantment.PROTECTION_FIRE);
		item.removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
		item.removeEnchantment(Enchantment.SILK_TOUCH);
		item.removeEnchantment(Enchantment.THORNS);
		item.removeEnchantment(Enchantment.WATER_WORKER);
	}
}
