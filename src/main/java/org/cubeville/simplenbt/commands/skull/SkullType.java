package org.cubeville.simplenbt.commands.skull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterShort;
import org.cubeville.commons.commands.CommandResponse;

public class SkullType extends Command {

	public SkullType() {
		super("skull type");
        setPermission("snbt.skull");
		addBaseParameter(new CommandParameterShort());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {

		ItemStack item = player.getInventory().getItemInMainHand();
		short data = (short) baseParameters.get(0);
		
		if (item.getType() != Material.SKULL_ITEM) throw new CommandExecutionException("&cHeld item must be a &6Skull&c!");
		if (data < 0 || data > 5) throw new CommandExecutionException("&cThe value &6" + baseParameters.get(0) + "&c is invalid. Please use a value from 0-5.");
		
		item.setDurability(data);
		
		if (data != 3) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			if (meta.getOwner() != null) meta.setOwner(null);
		}
		
		player.getInventory().setItemInMainHand(item);

		return new CommandResponse("&aSkull type changed.");
	}

}
