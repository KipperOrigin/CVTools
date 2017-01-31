package org.cubeville.simplenbt.commands.item.lore;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemLoreAdd extends Command {

	public ItemLoreAdd() {
		super("item lore add");
        setPermission("snbt.item.lore");
		addBaseParameter(new CommandParameterString());
		addParameter("insert", true, new CommandParameterInteger());
		addParameter("set", true, new CommandParameterInteger());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			throw new CommandExecutionException("&cMust be holding an item!");
		}
		
		NBTItem item = new NBTItem(player.getInventory().getItemInMainHand());
		String line = ColorUtils.addColor("&r" + (String) baseParameters.get(0));
		CommandResponse cr = new CommandResponse();
		
		if (!parameters.containsKey("insert") && !parameters.containsKey("set")) {
			item.addLore(line);
			cr.addMessage("&aLore added to item!");
		} else if (parameters.containsKey("insert") && !parameters.containsKey("set")) {
			item.insertLore((int) parameters.get("insert") - 1, line);
			cr.addMessage("&aLore inserted at line &6" + parameters.get("insert"));
		} else if (!parameters.containsKey("insert") && parameters.containsKey("set")) {
			item.replaceLore((int) parameters.get("set") - 1, line);
			cr.addMessage("&aLore set to line &6" + (int) parameters.get("set"));
		} else {
			throw new CommandExecutionException("&cCannot insert and set lore at the same time!");
		}
		
		player.getInventory().setItemInMainHand(item.asItemStack());
		return cr;
	}

}
