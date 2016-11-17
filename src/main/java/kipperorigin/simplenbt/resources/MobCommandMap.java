package kipperorigin.simplenbt.resources;

import java.util.Collections;
import java.util.Map;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MobCommandMap {
	public static Map<String, LivingEntity> eventCommands;
	
	public static void addEventCommand(Player player, LivingEntity entity) {
		eventCommands.put(player.getName(), entity);
	}
	
	public static void removeEventCommand(Player player) {
		if (eventCommands.containsKey(player.getName())) {
			eventCommands.remove(player.getName());
		}
	}
	
	public static void removeFromValue(LivingEntity entity) {
		eventCommands.values().removeAll(Collections.singleton(entity));
	}
	
	public static boolean containsKey(Player player) {
		return eventCommands.containsKey(player.getName());
	}
	
	public static boolean containsValue(LivingEntity entity) {
		return eventCommands.containsValue(entity);
	}
	
	public static LivingEntity getValue(Player player) {
		return eventCommands.get(player.getName());
	}
}
