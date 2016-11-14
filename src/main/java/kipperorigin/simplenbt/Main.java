package kipperorigin.simplenbt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import kipperorigin.simplenbt.commands.book.BookColorize;
import kipperorigin.simplenbt.commands.book.BookMain;
import kipperorigin.simplenbt.commands.book.BookSetAuthor;
import kipperorigin.simplenbt.commands.book.BookSetTitle;
import kipperorigin.simplenbt.commands.book.BookUnsign;
import kipperorigin.simplenbt.commands.commandparser.CommandParser;
import kipperorigin.simplenbt.commands.item.ItemAttributes;
import kipperorigin.simplenbt.commands.item.ItemFlags;

public class Main extends JavaPlugin {
	
	CommandParser commandParser;
	
	public void onEnable() {
        
		commandParser = new CommandParser();                                                                                                                                                   
        
		// ARMOR
		
		// BOOK
		commandParser.addCommand(new BookMain());
		commandParser.addCommand(new BookColorize());    
		commandParser.addCommand(new BookSetAuthor());
		commandParser.addCommand(new BookSetTitle());
		commandParser.addCommand(new BookUnsign());
		
		// FIREWORK
		
		// HEAD
		
		// ITEM	
		commandParser.addCommand(new ItemAttributes());
		commandParser.addCommand(new ItemFlags());
		
		// POTION
		
	}
	
	public void onDisable() {
		// TO-DO
	}

    @Override                                                                                                                                                                                  
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)                                                                                               
    {                                                                                                                                                                                          
        if(!(sender instanceof Player)) return false;                                                                                                                                          
        Player player = (Player)sender;                                                                                                                                                        
                                                                                                                                                                                               
        if(command.getName().equals("snbt")) {                                                                                                                                               
            return commandParser.execute(player, args);                                                                                                                                        
        }                                                                                                                                                                                      
        else {                                                                                                                                                                                 
            return false;                                                                                                                                                                      
        }                                                                                                                                                                                      
    }                                                                                                                                                                                          

	
}
