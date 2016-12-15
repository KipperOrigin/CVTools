package org.cubeville.cvtools.commands.simplenbt.item;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemGlow extends Command {	
	
	public ItemGlow() {
		super("item glow");
		addBaseParameter(new CommandParameterBoolean());
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getType() == null)
			return null;
		
		NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
		
		// if ((boolean) baseParameters.get(0))
			//nbtItem.removeGlow();
		// else if (!(boolean) baseParameters.get(0))
			nbtItem.addGlow();
		
		player.getInventory().setItemInMainHand(nbtItem.asItemStack());
                return null;
	}
	
}
