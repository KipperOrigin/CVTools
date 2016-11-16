package kipperorigin.simplenbt.commands.item.flags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterEnumeratedStringList;
import kipperorigin.simplenbt.nbt.NBTItem;

public class ItemFlags  extends Command {

	List<ItemFlag> addItemFlags = new ArrayList<>();
	List<ItemFlag> removeItemFlags = new ArrayList<>();
	ItemFlag itemFlag = null;
	
    public ItemFlags() {                                                                     
        super("item flags");
        Set<String> flags = new HashSet<String>(Arrays.asList("hide_attributes","hide_destroys","hide_potion_effects","hide_enchants","hide_placedon","hide_unbreakable"));
        addFlag("clearall");
        addFlag("hideall");
        addParameter("add", true, new CommandParameterEnumeratedStringList(flags));
        addParameter("remove", true, new CommandParameterEnumeratedStringList(flags));
    }

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		Set<String> removeArgs = (Set<String>) parameters.get("remove");
		Set<String> addArgs = (Set<String>) parameters.get("add");
		
		if (flags.contains("clearall"))
			item.clearFlags();
		
		if (flags.contains("hideall"))
			item.addAllFlags();
		
		if (parameters.get("add") != null) {
			if (addArgs != null)
				for (String string: addArgs) { 
					addItemFlags.add(ItemFlag.valueOf(string.toUpperCase()));
				}
			
				ItemFlag[] finalFlags = addItemFlags.toArray(new ItemFlag[0]);
				item.addFlags(finalFlags);
		}
		
		if (parameters.get("remove") != null) {
			if (removeArgs != null)
				for (String string: removeArgs) {
					removeItemFlags.add(ItemFlag.valueOf(string.toUpperCase()));
				}
			
				ItemFlag[] finalFlags = (ItemFlag[]) removeItemFlags.toArray();
				item.removeFlags(finalFlags);
		}
		
		player.getInventory().setItemInMainHand(item.asItemStack());
	}
}
