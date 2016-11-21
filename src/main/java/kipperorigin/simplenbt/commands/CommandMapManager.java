package kipperorigin.simplenbt.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class CommandMapManager {
	public static Map<String, Block> blockCommandMap;
	public static Map<String, Entity> entityCommandMap;
	public static Map<String, LivingEntity> livingEntityCommandMap;
	
	public static void registerCommandMaps() {
		blockCommandMap = new HashMap<>();
		entityCommandMap = new HashMap<>();
		livingEntityCommandMap = new HashMap<>();
	}
	
	public static void unregisterCommandMaps() {
		blockCommandMap = null;
		entityCommandMap = null;
		livingEntityCommandMap = null;
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
}
