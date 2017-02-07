package org.cubeville.cvtools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cubeville.commons.commands.CommandParser;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.commands.CommandManager;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.events.EventManager;
import org.cubeville.cvtools.events.ProtocolEventManager;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutManager;
import org.cubeville.teleportsign.TeleportSignManager;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

@SuppressWarnings("unused")
public class CVTools extends JavaPlugin {

    EventManager eventManager;
    ProtocolEventManager pmManager;
    LoadoutManager loadoutManager;
    TeleportSignManager tpSignManager;
    public static CVTools instance;

    public static CVTools getInstance() {
        return instance;
    }

    public LoadoutManager getLoadoutManager() {
        return loadoutManager;
    }
    
    public TeleportSignManager getTeleportSignManager() {
        return tpSignManager;
    }

    public void onEnable() {
        instance = this;

        ConfigurationSerialization.registerClass(LoadoutContainer.class, "LoadoutContainer");
        ConfigurationSerialization.registerClass(LoadoutManager.class, "LoadoutManager");

        CommandManager.registerAllCommands(this);
        CommandMapManager.registerMaps();
        eventManager = new EventManager(this);
        pmManager = new ProtocolEventManager(this);
        
        tpSignManager = (TeleportSignManager) getConfig().get("TeleportSignManager");
        if(tpSignManager == null) tpSignManager = new TeleportSignManager();

        loadoutManager = (LoadoutManager) getConfig().get("LoadoutManager");
        if(loadoutManager == null) loadoutManager = new LoadoutManager();
        loadoutManager.setManager(this);

        pmManager.registerEvents();
        eventManager.registerEvents();
    }

    public void onDisable() {
        getConfig().set("LoadoutManager", loadoutManager);
        getConfig().set("TeleportSignManager", tpSignManager);
        saveConfig();
        CommandManager.nullifyCommandParsers();
        CommandMapManager.unregisterMaps();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equals("snbt")) {
            return CommandManager.snbtCommandParser.execute(sender, args);
        } else if (command.getName().equals("cvtools")) {
            return CommandManager.toolsCommandParser.execute(sender, args);
        } else if (command.getName().equals("cvpvp")) {
            return CommandManager.pvpCommandParser.execute(sender, args);
        } else if (command.getName().equals("tpsign")) {
            return CommandManager.tpSignCommandParser.execute(sender, args);
        } else if (command.getName().equals("cmdextend")) {
        	if (args.length < 1 || !(sender instanceof Player)) return false;
        	if (args[0].equalsIgnoreCase("start")) {
        		if (args.length < 2) return false;
    			String addString = "";
        		for (int i = 1; i < args.length; i++) {
        			if (i != 1) addString += " ";
        			addString += args[i];
        		}
        		CommandMapManager.secondaryMap.put((Player) sender, addString);
        		sender.sendMessage(ColorUtils.addColor("&aCommand extenstion successfully started!"));
        	} else if (args[0].equalsIgnoreCase("add")) {
    			String addString = "";
        		for (int i = 1; i < args.length; i++) {
        			if (i != 1) addString += " ";
        			addString += args[i];
        		}
        		String lastString = (String) CommandMapManager.secondaryMap.get((Player) sender);
                if (lastString.endsWith("\\")) lastString = lastString.substring(0, lastString.length() - 1);
                else lastString += " ";
        		CommandMapManager.secondaryMap.put((Player) sender, lastString + addString);
        		sender.sendMessage(ColorUtils.addColor("&aCommand extenstion successfully added!"));
        	} else if (args[0].equalsIgnoreCase("finish")) {
        		if (args.length < 2) return false;
    			String addString = "";
        		for (int i = 1; i < args.length; i++) {
        			if (i != 1) addString += " ";
        			addString += args[i];
        		}
        		String lastString = (String) CommandMapManager.secondaryMap.get((Player) sender);
                if (lastString.endsWith("\\")) lastString = lastString.substring(0, lastString.length() - 1);
                else lastString += " ";
        		sender.sendMessage(ColorUtils.addColor("&aCommand extenstion successfully executed!"));
                ((Player) sender).performCommand(lastString + addString);
                CommandMapManager.secondaryMap.removePlayer((Player) sender);
        	}
        } else {
            return false;
        }
		return false;
    }
    
}
