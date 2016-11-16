package kipperorigin.simplenbt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import kipperorigin.simplenbt.commands.*;
import kipperorigin.simplenbt.commands.armor.ArmorColorByName;
import kipperorigin.simplenbt.commands.armor.ArmorColorByRGB;
import kipperorigin.simplenbt.commands.banner.BannerPatternAdd;
import kipperorigin.simplenbt.commands.book.BookColorize;
import kipperorigin.simplenbt.commands.book.BookMain;
import kipperorigin.simplenbt.commands.book.BookSetAuthor;
import kipperorigin.simplenbt.commands.book.BookSetTitle;
import kipperorigin.simplenbt.commands.book.BookUnsign;
import kipperorigin.simplenbt.commands.commandparser.CommandParser;
import kipperorigin.simplenbt.commands.firework.FireworkEffectAdd;
import kipperorigin.simplenbt.commands.firework.FireworkEffectClear;
import kipperorigin.simplenbt.commands.firework.FireworkEffectRemove;
import kipperorigin.simplenbt.commands.firework.FireworkSetPower;
import kipperorigin.simplenbt.commands.item.ItemSetDurability;
import kipperorigin.simplenbt.commands.item.ItemSetName;
import kipperorigin.simplenbt.commands.item.ItemSetType;
import kipperorigin.simplenbt.commands.item.attributes.ItemAttributesAdd;
import kipperorigin.simplenbt.commands.item.attributes.ItemAttributesClear;
import kipperorigin.simplenbt.commands.item.attributes.ItemAttributesRemove;
import kipperorigin.simplenbt.commands.item.enchantments.ItemEnchantmentsAdd;
import kipperorigin.simplenbt.commands.item.enchantments.ItemEnchantmentsClear;
import kipperorigin.simplenbt.commands.item.enchantments.ItemEnchantmentsRemove;
import kipperorigin.simplenbt.commands.item.flags.ItemFlags;
import kipperorigin.simplenbt.commands.item.flags.ItemFlagsAdd;
import kipperorigin.simplenbt.commands.item.flags.ItemFlagsAddAll;
import kipperorigin.simplenbt.commands.item.flags.ItemFlagsClear;
import kipperorigin.simplenbt.commands.item.flags.ItemFlagsRemove;
import kipperorigin.simplenbt.commands.item.lore.ItemLoreAdd;
import kipperorigin.simplenbt.commands.item.lore.ItemLoreClear;
import kipperorigin.simplenbt.commands.item.lore.ItemLoreRemove;
import kipperorigin.simplenbt.commands.player.PlayerEquipItem;
import kipperorigin.simplenbt.commands.potion.PotionEffectAdd;
import kipperorigin.simplenbt.commands.potion.PotionEffectClear;
import kipperorigin.simplenbt.commands.potion.PotionEffectRemove;
import kipperorigin.simplenbt.commands.potion.PotionSetType;
import kipperorigin.simplenbt.commands.skull.SkullSetOwner;
import kipperorigin.simplenbt.commands.skull.SkullSetType;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
	
	CommandParser commandParser;
	
	public void onEnable() {
        
		commandParser = new CommandParser();                                                                                                                                                   
        
		// ARMOR
		commandParser.addCommand(new ArmorColorByName());
		commandParser.addCommand(new ArmorColorByRGB());
		
		// BANNER
		commandParser.addCommand(new BannerPatternAdd());
		
		// BOOK
		commandParser.addCommand(new BookMain());
		commandParser.addCommand(new BookColorize());    
		commandParser.addCommand(new BookSetAuthor());
		commandParser.addCommand(new BookSetTitle());
		commandParser.addCommand(new BookUnsign());
		
		// FIREWORK
		commandParser.addCommand(new FireworkEffectAdd());
		commandParser.addCommand(new FireworkEffectClear());
		commandParser.addCommand(new FireworkEffectRemove());
		commandParser.addCommand(new FireworkSetPower());
		
		// ITEM	
		commandParser.addCommand(new ItemSetDurability());
		commandParser.addCommand(new ItemSetName());
		commandParser.addCommand(new ItemSetType());
		
		// ITEM --flags
		commandParser.addCommand(new ItemFlags());
		commandParser.addCommand(new ItemFlagsAdd());
		commandParser.addCommand(new ItemFlagsAddAll());
		commandParser.addCommand(new ItemFlagsClear());
		commandParser.addCommand(new ItemFlagsRemove());
		
		// ITEM --attributes
		commandParser.addCommand(new ItemAttributesAdd());
		commandParser.addCommand(new ItemAttributesClear());
		commandParser.addCommand(new ItemAttributesRemove());
		
		// ITEM --enchantments
		commandParser.addCommand(new ItemEnchantmentsAdd());
		commandParser.addCommand(new ItemEnchantmentsClear());
		commandParser.addCommand(new ItemEnchantmentsRemove());

		// ITEM --lore
		commandParser.addCommand(new ItemLoreAdd());
		commandParser.addCommand(new ItemLoreClear());
		commandParser.addCommand(new ItemLoreRemove());
		
		// PLAYER
		commandParser.addCommand(new PlayerEquipItem());
		
		// POTION
		commandParser.addCommand(new PotionEffectAdd());
		commandParser.addCommand(new PotionEffectClear());
		commandParser.addCommand(new PotionEffectRemove());
		commandParser.addCommand(new PotionSetType());
		
		// SKULL
		commandParser.addCommand(new SkullSetOwner());
		commandParser.addCommand(new SkullSetType());
		

	}
	
	public void onDisable() {
		// TO-DO
	}

    @Override                                                                                                                                                                                  
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)                                                                                               
    {                                                                                                                                                                                          
        if(!(sender instanceof Player)) return false;                                                                                                                                          
        Player player = (Player)sender;                                                                                                                                                        
                                                                                                                                                                                               
        if(command.getName().equals("snbt") && player.hasPermission("snbt.admin")) {                                                                                                                                               
            return commandParser.execute(player, args);                                                                                                                                        
        }                                                                                                                                                                                      
        else {                                                                                                                                                                                 
            return false;                                                                                                                                                                      
        }                                                                                                                                                                                      
    }                                                                                                                                                                                          

	
}
