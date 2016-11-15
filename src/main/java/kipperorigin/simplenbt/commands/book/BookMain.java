package kipperorigin.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.nbt.BookItem;

public class BookMain extends Command {

    public BookMain() {                                                                     
        super("book");
        addParameter("title", true, new CommandParameterString());
        addParameter("author", true, new CommandParameterString());
        addFlag("unsign");
        addFlag("colorize");
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		
		if (flags.contains("unsign"))
			book.unsign();
		
		if (flags.contains("colorize"))
			book.colorizeBook();
		
		book.setAuthor((String) parameters.get("author"));
		book.setTitle((String) parameters.get("title"));
		
		player.getInventory().setItemInMainHand(book.asItemStack());

	}
}
