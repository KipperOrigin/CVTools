package kipperorigin.simplenbt.commands.item.attributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;
import kipperorigin.simplenbt.nbt.NBTItem;

public class ItemAttributesRemove extends Command {

    public ItemAttributesRemove() {                                                                     
        super("item attributes remove");
        addTextParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return;
		
		int i = (int) textParameters.get(0);
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		
		nbtItem.removeAttribute(i);
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
	}
}
