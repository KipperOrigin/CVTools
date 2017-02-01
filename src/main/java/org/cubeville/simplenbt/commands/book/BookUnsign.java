package org.cubeville.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.nbt.BookItem;

public class BookUnsign extends Command {

    public BookUnsign() {                                                                     
        super("book unsign");
        setPermission("snbt.book");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK) {
			throw new CommandExecutionException("&cMust be holding a &6written book&c!");
		}
		
		BookItem bookItem = new BookItem(player.getInventory().getItemInMainHand());
		// (Disables until permissions are complete) if (!bookItem.getAuthor().equalsIgnoreCase(player.getName()) && !player.hasPermission("snbt.book.admin")) throw new CommandExecutionException("&cYou do not have permission to unsign other players books!");
		bookItem.unsign();
		player.getInventory().setItemInMainHand(bookItem.asItemStack());
		
		return new CommandResponse("&aBook successfully unsigned!");
	} 
}
