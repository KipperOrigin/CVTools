package org.cubeville.cvtools.commands.commandmap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class CommandMap {
	
	public Map<String, Object> commandMap;
	
	public CommandMap() {
		commandMap = new HashMap<>();
	}
	
	public void put(Player player, Object value) {
		commandMap.put(player.getName(), value);
	}
	
	public void removePlayer(Player player) {
		commandMap.remove(player.getName());
	}

	public boolean contains(Player player) {
		return commandMap.containsKey(player.getName());
	}
	
	public boolean contains(Object value) {
		if (commandMap.containsValue(value))
			return true;
		else
			return false;
	}
	
	public Object get(Player player) {
		return commandMap.get(player.getName());
	}
	
	public Map<String, Object> getRawMap() {
		return commandMap;
	}
	
	public Object getUnsafe(Player player) throws CommandMapValueException {
		if (commandMap.containsKey(player.getName()))
			if (commandMap.get(player.getName()) == null)
				throw new CommandMapValueException("Command map has " + player.getName() + " but contains no value!");
			else 
				return (commandMap.get(player.getName()));
		else
			throw new CommandMapValueException("Command map does not contain " + player.getName() + "!");
	}
}
