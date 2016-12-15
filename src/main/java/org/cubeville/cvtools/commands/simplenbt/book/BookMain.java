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

public class BookMain extends Command {

    public BookMain() {                                                                     
        super("book");
        addParameter("title", true, new CommandParameterString());
        addParameter("author", true, new CommandParameterString());
        addFlag("unsign");
        addFlag("colorize");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
                    return null;;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		
		if (flags.contains("unsign"))
			book.unsign();
		
		if (flags.contains("colorize"))
			book.colorizeBook();
		
		book.setAuthor((String) parameters.get("author"));
		book.setTitle((String) parameters.get("title"));
		
		player.getInventory().setItemInMainHand(book.asItemStack());
                return null;
	}
}
