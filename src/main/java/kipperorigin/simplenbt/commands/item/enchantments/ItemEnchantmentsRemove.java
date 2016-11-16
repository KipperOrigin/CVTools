package kipperorigin.simplenbt.commands.item.enchantments;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterEnchantment;

public class ItemEnchantmentsRemove extends Command {

	public ItemEnchantmentsRemove() {                                                                     
        super("item enchant remove");
        addTextParameter(new CommandParameterEnchantment());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		item.removeEnchantment((Enchantment) textParameters.get(0));
	}

}
