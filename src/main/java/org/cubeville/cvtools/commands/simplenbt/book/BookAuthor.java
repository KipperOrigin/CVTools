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

public class BookAuthor extends Command {

    public BookAuthor() {                                                                     
        super("book author");
        setPermission("snbt.book");
        addBaseParameter(new CommandParameterString());
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK) {
            throw new CommandExecutionException("&cMust be holding a &6written book&c!");
        }
        BookItem book = new BookItem(player.getInventory().getItemInMainHand());
        book.setAuthor((String) baseParameters.get(0));
        player.getInventory().setItemInMainHand(book.asItemStack());
       	return new CommandResponse("&aBook author successfully set to &6" + baseParameters.get(0));
    }
}
