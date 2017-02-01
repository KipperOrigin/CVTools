package org.cubeville.simplenbt.commands.item.attributes;

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
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.Attributes;
import org.cubeville.cvtools.nbt.NBTEntityLiving;
import org.cubeville.cvtools.nbt.NBTItem;
import org.cubeville.cvtools.nbt.Attributes.AttributeType;

public class ItemAttributesAdd extends Command {

    public ItemAttributesAdd() {                                                                     
        super("item attributes add");
        setPermission("snbt.item.attributes");
        addBaseParameter(new CommandParameterEnum(NBTEntityLiving.AttributeType.class));
        addBaseParameter(new CommandParameterDouble());
        addParameter("slot", true, new CommandParameterEnum(Attributes.EquipmentSlot.class));
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}

		double d = (double) baseParameters.get(1);
		AttributeType type = AttributeType.fromId(((NBTEntityLiving.AttributeType) baseParameters.get(0)).toString());
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		String slot = "mainhand";
		
		if (parameters.containsKey("slot")) {
			slot = ((Attributes.EquipmentSlot) parameters.get("slot")).toString();
		}
		
		if (type == AttributeType.GENERIC_ATTACK_DAMAGE)
			d -= 1;
		else if (type == AttributeType.GENERIC_ATTACK_SPEED)
			d -= 4;
		
		nbtItem.addAttribute(((NBTEntityLiving.AttributeType) baseParameters.get(0)).toString(), type, d, slot);
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
        return new CommandResponse("&aAttribute added!");
	}
}
