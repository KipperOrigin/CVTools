package kipperorigin.simplenbt.commands.book;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;
import org.cubeville.commons.CommandParameterString;

import kipperorigin.simplenbt.nbt.BookItem;

public class BookSetTitle extends Command {

    public BookSetTitle() {                                                                     
        super("book title");  
        addBaseParameter(new CommandParameterString());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		if (player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
			return;
		BookItem book = new BookItem(player.getInventory().getItemInMainHand());
		book.setTitle((String) baseParameters.get(0));
		player.getInventory().setItemInMainHand(book.asItemStack());
	} 
}
