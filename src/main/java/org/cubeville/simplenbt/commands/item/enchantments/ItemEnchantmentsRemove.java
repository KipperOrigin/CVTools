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
import org.cubeville.commons.commands.CommandParameterEnchantment;
import org.cubeville.commons.commands.CommandResponse;

public class ItemEnchantmentsRemove extends Command {

	public ItemEnchantmentsRemove() {                                                                     
        super("item enchant remove");
        setPermission("snbt.item.enchantments");
        addBaseParameter(new CommandParameterEnchantment());
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}
			
		item.removeEnchantment((Enchantment) baseParameters.get(0));

		return new CommandResponse("&aEnchantment &6" + ((Enchantment) baseParameters.get(0)).getName() + "&aremoved from item!");
	}

}
