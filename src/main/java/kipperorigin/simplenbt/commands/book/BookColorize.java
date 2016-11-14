package kipperorigin.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.resources.BookItem;

public class BookColorize extends Command {

    public BookColorize() {                                                                     
        super("book colorize");                                                                         
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem bookItem = new BookItem(player.getInventory().getItemInMainHand());
		bookItem.colorizeBook();
		player.getInventory().setItemInMainHand(bookItem.asItemStack());
	} 
}