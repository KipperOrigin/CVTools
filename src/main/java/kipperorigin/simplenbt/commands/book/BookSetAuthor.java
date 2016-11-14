package kipperorigin.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.resources.BookItem;

public class BookSetAuthor extends Command {

	public BookSetAuthor() {                                                                     
		super("book author");  
		addTextParameter(new CommandParameterString());
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		book.setAuthor((String) textParameters.get(0));
		player.getInventory().setItemInMainHand(book.asItemStack());
	}
}
