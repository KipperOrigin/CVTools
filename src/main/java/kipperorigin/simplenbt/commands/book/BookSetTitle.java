package kipperorigin.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.nbt.BookItem;

public class BookSetTitle extends Command {

    public BookSetTitle() {                                                                     
        super("book title");  
        addTextParameter(new CommandParameterString());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		book.setTitle((String) textParameters.get(0));
		player.getInventory().setItemInMainHand(book.asItemStack());
	} 
}
