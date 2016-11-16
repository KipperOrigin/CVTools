package kipperorigin.simplenbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterEnum;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterShort;

public class ItemSetType extends Command {

	public ItemSetType() {
		super("item type");
		addTextParameter(new CommandParameterEnum(Material.class));
		addParameter("data", true, new CommandParameterShort());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		item.setType((Material) textParameters.get(0));
		
		if (parameters.containsKey("data"))
			item.setDurability((short) parameters.get("data"));
	}

}
