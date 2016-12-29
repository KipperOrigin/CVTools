package org.cubeville.cvtools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cubeville.commons.commands.CommandParser;
import org.cubeville.cvtools.commands.CommandManager;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.events.EventManager;
import org.cubeville.cvtools.events.ProtocolEventManager;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

@SuppressWarnings("unused")
public class CVTools extends JavaPlugin {
	
    EventManager eventManager;
    ProtocolEventManager pmManager;
    public LoadoutManager loadoutManager;
    public static CVTools instance;

    public static CVTools getInstance() {
        return instance;
    }

    public LoadoutManager getLoadoutManager() {
        return loadoutManager;
    }
    
    public void onEnable() {
        instance = this;
        
        ConfigurationSerialization.registerClass(LoadoutContainer.class, "LoadoutContainer");
        ConfigurationSerialization.registerClass(LoadoutManager.class, "LoadoutManager");
		
        CommandManager.registerAllCommands(this);
        CommandMapManager.registerMaps();
        eventManager = new EventManager(this);
        pmManager = new ProtocolEventManager(this);

        loadoutManager = (LoadoutManager) getConfig().get("LoadoutManager");
        if(loadoutManager == null) loadoutManager = new LoadoutManager();
        
        pmManager.registerEvents();
        eventManager.registerEvents();
    }
	
    public void onDisable() {
        getConfig().set("LoadoutManager", loadoutManager);
        saveConfig();
        CommandManager.nullifyCommandParsers();
        CommandMapManager.unregisterMaps();
    }
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player)) return false;
        Player player = (Player)sender;

        if(command.getName().equals("snbt")) {   
            if (!player.hasPermission("snbt.admin")) {
                player.sendMessage("Need permissions to use!");
                return false;
            } else 
                return CommandManager.snbtCommandParser.execute(player, args);

        } else if (command.getName().equals("cvtools")) {
            if (!player.hasPermission("cvtools.admin")) {
                player.sendMessage("Need permissions to use!");
                return false;
            } else
                return CommandManager.toolsCommandParser.execute(player, args);
        	
        } else if (command.getName().equals("pvp")) {
            if (!player.hasPermission("cvpvp.admin")) {
                player.sendMessage("Need permissions to use!");
                return false;
            } else
                return CommandManager.pvpCommandParser.execute(player, args);
        } else {
            return false;
        }
    }
    
}
