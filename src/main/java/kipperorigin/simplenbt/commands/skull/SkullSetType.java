package kipperorigin.simplenbt.commands.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterShort;

public class SkullSetType extends Command {

	public SkullSetType() {
		super("skull type");
		addTextParameter(new CommandParameterShort());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {

		ItemStack item = player.getInventory().getItemInMainHand();
		short data = (short) textParameters.get(0);
		
		if (item.getType() != Material.SKULL_ITEM)
			return;

		if (data < 0 || data > 5)
			return;
		
		item.setDurability(data);
		
		player.getInventory().setItemInMainHand(item);
	}

}
