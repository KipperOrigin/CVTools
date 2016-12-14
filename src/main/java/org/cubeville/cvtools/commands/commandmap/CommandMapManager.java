package org.cubeville.cvtools.commands.commandmap;

public class CommandMapManager {
	
	public static CommandMap primaryMap;
	public static CommandMap secondaryMap;

	public void registerMaps() {
		primaryMap = new CommandMap();
		secondaryMap = new CommandMap();
	}
	
	public void unregisterMaps() {
		primaryMap = null;
		secondaryMap = null;
	}
}
