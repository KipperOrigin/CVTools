package kipperorigin.simplenbt.commands.item;

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
import kipperorigin.simplenbt.resources.Attributes.AttributeType;
import kipperorigin.simplenbt.resources.NBTItem;

public class ItemAttributes extends Command {

    public ItemAttributes() {                                                                     
        super("item attributes");
        addTextParameter(new CommandParameterString());
        addTextParameter(new CommandParameterDouble());
        addParameter("slot", true, new CommandParameterString());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return;
		
		String typeName = (String) textParameters.get(0);
		double d = (double) textParameters.get(0);
		AttributeType type = getAttributeTypeByName(typeName);
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
	
	public AttributeType getAttributeTypeByName(String string) {
		if (string.equalsIgnoreCase("armor")) {
			return AttributeType.GENERIC_ARMOR;
		} else if (string.equalsIgnoreCase("toughness")) {
			return AttributeType.GENERIC_ARMOR_TOUGHNESS;
		} else if (string.equalsIgnoreCase("damage")) {
			return AttributeType.GENERIC_ATTACK_DAMAGE;
		} else if (string.equalsIgnoreCase("attspeed")) {
			return AttributeType.GENERIC_ATTACK_SPEED;
		} else if (string.equalsIgnoreCase("kbresist")) {
			return AttributeType.GENERIC_KNOCKBACK_RESISTANCE;
		} else if (string.equalsIgnoreCase("health")) {
			return AttributeType.GENERIC_MAX_HEALTH;
		} else if (string.equalsIgnoreCase("movespeed")) {
			return AttributeType.GENERIC_MOVEMENT_SPEED;
		} else
			return null;
	}
}
