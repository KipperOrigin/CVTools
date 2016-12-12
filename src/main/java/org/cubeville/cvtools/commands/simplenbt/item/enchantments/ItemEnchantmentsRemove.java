package org.cubeville.cvtools.commands.simplenbt.item.enchantments;

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

public class ItemEnchantmentsRemove extends Command {

	public ItemEnchantmentsRemove() {                                                                     
        super("item enchant remove");
        addBaseParameter(new CommandParameterEnchantment());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		item.removeEnchantment((Enchantment) baseParameters.get(0));
	}

}
