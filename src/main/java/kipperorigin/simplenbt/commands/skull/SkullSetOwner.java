package kipperorigin.simplenbt.commands.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;

public class SkullSetOwner extends Command {

	public SkullSetOwner() {
		super("skull owner");
		addTextParameter(new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		SkullMeta meta;

		if (item.getType() != Material.SKULL_ITEM)
			return;

		meta = (SkullMeta) player.getInventory().getItemInMainHand().getItemMeta();
		meta.setOwner((String) textParameters.get(0));

		item.setDurability((short) 3);
		item.setItemMeta(meta);
		player.getInventory().setItemInMainHand(item);
	}
}



