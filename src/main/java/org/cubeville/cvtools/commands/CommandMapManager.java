package org.cubeville.cvtools.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandMapManager {
	public static Map<String, Block> blockCommandMap;
	public static Map<String, Entity> entityCommandMap;
	public static Map<String, LivingEntity> livingEntityCommandMap;
	public static Map<String, Long> stopWatchCommandMap;
	
	public static void registerCommandMaps() {
		blockCommandMap = new HashMap<>();
		entityCommandMap = new HashMap<>();
		livingEntityCommandMap = new HashMap<>();
		stopWatchCommandMap = new HashMap<>();
	}
	
	public static void unregisterCommandMaps() {
		blockCommandMap = null;
		entityCommandMap = null;
		livingEntityCommandMap = null;
		stopWatchCommandMap = null;
	}
	
	public static Map<String, Block> getBlockCommandMap() {
		return blockCommandMap;
	}
	
	public static Map<String, Entity> getEntityCommandMap() {
		return entityCommandMap;
	}
	
	public static Map<String, LivingEntity> getLivingEntityCommandMap() {
		return livingEntityCommandMap;
	}
	
	public static Map<String, Long> getStopWatchCommandMap() {
		return stopWatchCommandMap;
	}
	
	public static void removePlayerFromAll(Player player) {
		String name = player.getName();
		
		if (blockCommandMap.containsKey(name))
			blockCommandMap.remove(name);
		if (entityCommandMap.containsKey(name))
			entityCommandMap.remove(name);
		if (livingEntityCommandMap.containsKey(name))
			livingEntityCommandMap.remove(name);
		if (stopWatchCommandMap.containsKey(name))
			stopWatchCommandMap.remove(name);
	}
}
