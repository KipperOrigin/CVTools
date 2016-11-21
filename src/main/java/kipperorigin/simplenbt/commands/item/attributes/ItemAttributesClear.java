package kipperorigin.simplenbt.commands.item.attributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;

import kipperorigin.simplenbt.nbt.NBTItem;

public class ItemAttributesClear extends Command {

    public ItemAttributesClear() {                                                                     
        super("item attributes clear");
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return;
		
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		
		nbtItem.clearAttributes();
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
	}
}
