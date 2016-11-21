package kipperorigin.simplenbt.commands.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.Command;
import org.cubeville.commons.CommandParameterEnum;
import org.cubeville.commons.CommandParameterShort;

public class ItemSetType extends Command {

	public ItemSetType() {
		super("item type");
		addBaseParameter(new CommandParameterEnum(Material.class));
		addParameter("data", true, new CommandParameterShort());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item == null || item.getType() == Material.AIR)
			return;
		
		item.setType((Material) baseParameters.get(0));
		
		if (parameters.containsKey("data"))
			item.setDurability((short) parameters.get("data"));
	}

}
