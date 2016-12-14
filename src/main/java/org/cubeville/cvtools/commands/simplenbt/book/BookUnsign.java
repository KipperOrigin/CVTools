package org.cubeville.cvtools.commands.simplenbt.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.cvtools.nbt.BookItem;

public class BookUnsign extends Command {

    public BookUnsign() {                                                                     
        super("book unsign");                                                                         
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem bookItem = new BookItem(player.getInventory().getItemInMainHand());
		bookItem.unsign();
		player.getInventory().setItemInMainHand(bookItem.asItemStack());
	} 
}