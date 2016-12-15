package org.cubeville.cvtools.commands.simplenbt.item.attributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterDouble;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;
import org.cubeville.cvtools.nbt.Attributes.AttributeType;

public class ItemAttributesAdd extends Command {

    public ItemAttributesAdd() {                                                                     
        super("item attributes add");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterDouble());
        addParameter("slot", true, new CommandParameterString());
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return null;
		
		String typeName = (String) baseParameters.get(0);
		double d = (double) baseParameters.get(1);
		AttributeType type = AttributeType.getAttributeTypeByName(typeName);
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		List<String> slots = new ArrayList<String>(Arrays.asList("mainhand","offhand","head","chest","legs","feet"));
		String slot = "mainhand";
		
		if (slots.contains((String) parameters.get("slot")))
				slot = (String) parameters.get("slot");
		
		if (type == null)
			return null;
		else if (typeName.equalsIgnoreCase("damage"))
			d -= 1;
		else if (typeName.equalsIgnoreCase("attspeed"))
			d -= 4;
		
		nbtItem.addAttribute(typeName, type, d, slot);
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
                return null;
	}
}
