package org.cubeville.cvtools.commands.simplenbt.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.BookItem;

public class BookSetAuthor extends Command {

	public BookSetAuthor() {                                                                     
		super("book author");  
		addBaseParameter(new CommandParameterString());
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return null;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		book.setAuthor((String) baseParameters.get(0));
		player.getInventory().setItemInMainHand(book.asItemStack());
                return null;
	}
}
