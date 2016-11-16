package kipperorigin.simplenbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterShort;

public class ItemSetDurability extends Command {

	public ItemSetDurability() {
		super("item durability");
		addParameter("set", true, new CommandParameterShort());
		addFlag("unbreakable");
		addFlag("max");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		if (parameters.containsKey("set") && !flags.contains("unbreakable") && !flags.contains("max"))
			item.setDurability((short) (item.getType().getMaxDurability() - ((short) parameters.get("set"))));
		else if (!parameters.containsKey("set") && flags.contains("unbreakable") && !flags.contains("max")) {
			ItemMeta meta = item.getItemMeta();
			if (meta.spigot().isUnbreakable())
				meta.spigot().setUnbreakable(false);
			else
				meta.spigot().setUnbreakable(true);
			item.setItemMeta(meta);
		}
		else if (!parameters.containsKey("set") && !flags.contains("unbreakable") && flags.contains("max"))
			item.setDurability((short) 0);
		else
			return;
		
	}

}
