package org.cubeville.cvtools.commands.simplenbt.item.lore;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.nbt.NBTItem;

public class ItemLoreAdd extends Command {

	public ItemLoreAdd() {
		super("item lore add");
		addBaseParameter(new CommandParameterString());
		addParameter("insert", true, new CommandParameterInteger());
		addParameter("set", true, new CommandParameterInteger());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		NBTItem item;
		
		try {
			item = new NBTItem(player.getInventory().getItemInMainHand());
		} catch (NullPointerException e) {
			return null;
		}
		
		String line = Colorize.addColor("&r" + (String) baseParameters.get(0));
		
		if (!parameters.containsKey("insert") && !parameters.containsKey("set"))
			item.addLore(line);
		else if (parameters.containsKey("insert") && !parameters.containsKey("set"))
			item.insertLore((int) parameters.get("insert") - 1, line);
		else if (!parameters.containsKey("insert") && parameters.containsKey("set"))
			item.replaceLore((int) parameters.get("set") - 1, line);
		
		player.getInventory().setItemInMainHand(item.asItemStack());

                return null;
	}

}
