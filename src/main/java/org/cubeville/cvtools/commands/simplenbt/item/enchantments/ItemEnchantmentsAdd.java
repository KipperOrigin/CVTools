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
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

public class ItemEnchantmentsAdd extends Command {

    public ItemEnchantmentsAdd() {                                                                     
        super("item enchant add");
        addBaseParameter(new CommandParameterEnchantment());
        addBaseParameter(new CommandParameterInteger());
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return null;
		
		item.addUnsafeEnchantment((Enchantment) baseParameters.get(0), (int) baseParameters.get(1));
                return null;
	}

}
