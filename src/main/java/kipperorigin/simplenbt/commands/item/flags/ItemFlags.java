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

	List<ItemFlag> addItemFlags = new ArrayList<ItemFlag>();
	List<ItemFlag> removeItemFlags = new ArrayList<ItemFlag>();
	ItemFlag itemFlag = null;
	
    public ItemFlags() {                                                                     
        super("item flags");
        Set<String> flags = new HashSet<String>(Arrays.asList("attributes","destroys","effects","enchants","placedon","unbreakable"));
        addFlag("clearall");
        addFlag("hideall");
        addParameter("add", true, new CommandParameterEnumeratedStringList(flags));
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
			for (String string: addArgs) {
				itemFlag = this.getFlagByName(string);
				if (itemFlag != null)
					addItemFlags.add(itemFlag);
			}
			if (addArgs != null)
				item.removeFlags(addItemFlags);
		}
		
		if (parameters.get("remove") != null) {
			for (String string: removeArgs) {
				itemFlag = this.getFlagByName(string);
				if (itemFlag != null)
					removeItemFlags.add(itemFlag);
			}
			if (removeArgs != null)
				item.removeFlags(removeItemFlags);
		}
		
		player.getInventory().setItemInMainHand(item.asItemStack());
	}

	public ItemFlag getFlagByName(String string) {
		if (string.equalsIgnoreCase("attributes"))
			return ItemFlag.HIDE_ATTRIBUTES;
		else if (string.equalsIgnoreCase("destroys"))
			return ItemFlag.HIDE_DESTROYS;
		else if (string.equalsIgnoreCase("effects"))
			return ItemFlag.HIDE_POTION_EFFECTS;
		else if (string.equalsIgnoreCase("enchants"))
			return ItemFlag.HIDE_ENCHANTS;
		else if (string.equalsIgnoreCase("placedon"))
			return ItemFlag.HIDE_PLACED_ON;
		else if (string.equalsIgnoreCase("unbreakable"))
			return ItemFlag.HIDE_UNBREAKABLE;
		else return null;
	}
}
