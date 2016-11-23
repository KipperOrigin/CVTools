package org.cubeville.cvtools;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cubeville.commons.commands.CommandParser;
import org.cubeville.cvtools.commands.CommandManager;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.commands.simplenbt.*;
import org.cubeville.cvtools.commands.simplenbt.armor.ArmorColorByName;
import org.cubeville.cvtools.commands.simplenbt.armor.ArmorColorByRGB;
import org.cubeville.cvtools.commands.simplenbt.banner.BannerPatternAdd;
import org.cubeville.cvtools.commands.simplenbt.book.BookColorize;
import org.cubeville.cvtools.commands.simplenbt.book.BookMain;
import org.cubeville.cvtools.commands.simplenbt.book.BookSetAuthor;
import org.cubeville.cvtools.commands.simplenbt.book.BookSetTitle;
import org.cubeville.cvtools.commands.simplenbt.book.BookUnsign;
import org.cubeville.cvtools.commands.simplenbt.firework.FireworkEffectAdd;
import org.cubeville.cvtools.commands.simplenbt.firework.FireworkEffectClear;
import org.cubeville.cvtools.commands.simplenbt.firework.FireworkEffectRemove;
import org.cubeville.cvtools.commands.simplenbt.firework.FireworkSetPower;
import org.cubeville.cvtools.commands.simplenbt.item.ItemSetDurability;
import org.cubeville.cvtools.commands.simplenbt.item.ItemSetName;
import org.cubeville.cvtools.commands.simplenbt.item.ItemSetType;
import org.cubeville.cvtools.commands.simplenbt.item.attributes.ItemAttributesAdd;
import org.cubeville.cvtools.commands.simplenbt.item.attributes.ItemAttributesClear;
import org.cubeville.cvtools.commands.simplenbt.item.attributes.ItemAttributesRemove;
import org.cubeville.cvtools.commands.simplenbt.item.enchantments.ItemEnchantmentsAdd;
import org.cubeville.cvtools.commands.simplenbt.item.enchantments.ItemEnchantmentsClear;
import org.cubeville.cvtools.commands.simplenbt.item.enchantments.ItemEnchantmentsRemove;
import org.cubeville.cvtools.commands.simplenbt.item.flags.ItemFlags;
import org.cubeville.cvtools.commands.simplenbt.item.flags.ItemFlagsAdd;
import org.cubeville.cvtools.commands.simplenbt.item.flags.ItemFlagsAddAll;
import org.cubeville.cvtools.commands.simplenbt.item.flags.ItemFlagsClear;
import org.cubeville.cvtools.commands.simplenbt.item.flags.ItemFlagsRemove;
import org.cubeville.cvtools.commands.simplenbt.item.lore.ItemLoreAdd;
import org.cubeville.cvtools.commands.simplenbt.item.lore.ItemLoreClear;
import org.cubeville.cvtools.commands.simplenbt.item.lore.ItemLoreRemove;
import org.cubeville.cvtools.commands.simplenbt.player.PlayerEquipItem;
import org.cubeville.cvtools.commands.simplenbt.potion.PotionEffectAdd;
import org.cubeville.cvtools.commands.simplenbt.potion.PotionEffectClear;
import org.cubeville.cvtools.commands.simplenbt.potion.PotionEffectRemove;
import org.cubeville.cvtools.commands.simplenbt.potion.PotionSetType;
import org.cubeville.cvtools.commands.simplenbt.skull.SkullSetOwner;
import org.cubeville.cvtools.commands.simplenbt.skull.SkullSetType;
import org.cubeville.cvtools.events.EventManager;
import org.cubeville.cvtools.events.ProtocolEventManager;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

@SuppressWarnings("unused")
public class CVTools extends JavaPlugin {
	
	EventManager eventManager;
	ProtocolEventManager pmManager;
	
	public void onEnable() {   
		CommandManager.registerAllCommands(this);
		eventManager = new EventManager(this);
		pmManager = new ProtocolEventManager(this);
		
		pmManager.registerEvents();
		eventManager.registerEvents();
	}
	
	public void onDisable() {
		CommandManager.nullifyCommandParsers();
		CommandMapManager.unregisterCommandMaps();
	}

    @Override                                                                                                                                                                                  
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)                                                                                               
    {                                                                                                                                                                                          
        if(!(sender instanceof Player)) return false;                                                                                                                                          
        Player player = (Player)sender;                                                                                                                                                        
                                                                                                                                                                                               
        if(command.getName().equals("snbt") && player.hasPermission("snbt.admin")) {                                                                                                                                               
            return CommandManager.snbtCommandParser.execute(player, args);                                                                                                                                        
        } else if (command.getName().equals("tools") && player.hasPermission("snbt.stopwatch")) {
        	return CommandManager.toolsCommandParser.execute(player, args);
        } else {                                                                                                                                                                                 
            return false;                                                                                                                                                                      
        }                                                                                                                                                                                      
    }          
  
}
