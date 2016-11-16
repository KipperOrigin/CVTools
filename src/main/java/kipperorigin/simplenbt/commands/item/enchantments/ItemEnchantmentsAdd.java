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
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;

public class ItemEnchantmentsAdd extends Command {

    public ItemEnchantmentsAdd() {                                                                     
        super("item enchant add");
        addTextParameter(new CommandParameterEnchantment());
        addTextParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		item.addUnsafeEnchantment((Enchantment) textParameters.get(0), (int) textParameters.get(1));
	}

}
