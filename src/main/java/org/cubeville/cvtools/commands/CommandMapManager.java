package org.cubeville.cvtools.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class CommandMapManager {
	
    public static CommandMap primaryMap;
    public static CommandMap secondaryMap;
    public static Map<String, Long> stopwatchMap;

    public static void registerMaps() {
        primaryMap = new CommandMap();
        secondaryMap = new CommandMap();
        stopwatchMap = new HashMap<>();
    }
	
    public static void unregisterMaps() {
        primaryMap = null;
        secondaryMap = null;
    }
	
    public static void removePlayerFromAllMaps(Player player) {
        primaryMap.removePlayer(player);
        secondaryMap.removePlayer(player);
        if (primaryMap.contains(player)) {
            primaryMap.replaceValues(player, null);
        }
        if (secondaryMap.contains(player)) {
            primaryMap.replaceValues(player, null);
        }
    }
    
    public static boolean playerIsNull(Player player) {
    	if (primaryMap.contains(player)) {
    		if (primaryMap.get(player) == null) {
    			return true;
    		} else return false;
    	} else return false;
    }
	
}
