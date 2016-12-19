package org.cubeville.cvtools.commands;

import org.bukkit.entity.Player;

public class CommandMapManager {
	
	public static CommandMap primaryMap;
	public static CommandMap secondaryMap;

	public static void registerMaps() {
		primaryMap = new CommandMap();
		secondaryMap = new CommandMap();
	}
	
	public static void unregisterMaps() {
		primaryMap = null;
		secondaryMap = null;
	}
	
	public static void removePlayerFromAllMaps(Player player) {
		primaryMap.removePlayer(player);
		secondaryMap.removePlayer(player);
	}
	
}
