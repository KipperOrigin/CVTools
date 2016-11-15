package kipperorigin.simplenbt.commands.item.attributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterDouble;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.nbt.NBTItem;
import kipperorigin.simplenbt.nbt.Attributes.AttributeType;

public class ItemAttributesAdd extends Command {

    public ItemAttributesAdd() {                                                                     
        super("item attributes add");
        addTextParameter(new CommandParameterString());
        addTextParameter(new CommandParameterDouble());
        addParameter("slot", true, new CommandParameterString());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return;
		
		String typeName = (String) textParameters.get(0);
		double d = (double) textParameters.get(1);
		AttributeType type = AttributeType.getAttributeTypeByName(typeName);
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		List<String> slots = new ArrayList<String>(Arrays.asList("mainhand","offhand","head","chest","legs","feet"));
		String slot = "mainhand";
		
		if (slots.contains((String) parameters.get("slot")))
				slot = (String) parameters.get("slot");
		
		if (type == null)
			return;
		else if (typeName.equalsIgnoreCase("damage"))
			d -= 1;
		else if (typeName.equalsIgnoreCase("attspeed"))
			d -= 4;
		
		nbtItem.addAttribute(typeName, type, d, slot);
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
	}
}