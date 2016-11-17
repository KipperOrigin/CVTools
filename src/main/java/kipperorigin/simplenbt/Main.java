package kipperorigin.simplenbt;

import java.util.HashMap;
import java.util.Map;

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
import kipperorigin.simplenbt.events.EventManager;
import kipperorigin.simplenbt.resources.MobCommandMap;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
	
	EventManager eventManager;
	
	public void onEnable() {   
		CommandManager.registerCommands();
		eventManager = new EventManager(this);
		MobCommandMap.eventCommands = new HashMap<>();
		
		eventManager.registerEvents();
	}
	
	public void onDisable() {
		CommandManager.commandParser = null;
		MobCommandMap.eventCommands = null;
	}

    @Override                                                                                                                                                                                  
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)                                                                                               
    {                                                                                                                                                                                          
        if(!(sender instanceof Player)) return false;                                                                                                                                          
        Player player = (Player)sender;                                                                                                                                                        
                                                                                                                                                                                               
        if(command.getName().equals("snbt") && player.hasPermission("snbt.admin")) {                                                                                                                                               
            return CommandManager.commandParser.execute(player, args);                                                                                                                                        
        }                                                                                                                                                                                      
        else {                                                                                                                                                                                 
            return false;                                                                                                                                                                      
        }                                                                                                                                                                                      
    }          
  
}
