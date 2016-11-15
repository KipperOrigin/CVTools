package kipperorigin.simplenbt.commands.item.enchantments;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterEnum;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.nbt.NBTItem;
import net.minecraft.server.v1_9_R2.Enchantment;
import net.minecraft.server.v1_9_R2.EnchantmentSlotType;
import net.minecraft.server.v1_9_R2.Enchantments;

public class ItemEnchantmentsAdd extends Command {

    public ItemEnchantmentsAdd() {                                                                     
        super("item attributes add");
        addTextParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		// item.addEnchantment(ench, level);
		// TODO Auto-generated method stub
	}

}
