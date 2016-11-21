package kipperorigin.simplenbt.events;

import java.util.Collections;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;

import kipperorigin.simplenbt.commands.CommandMapManager;

public class EventEntityDeath implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		removeEntity(event.getEntity());
	}
	
	@EventHandler
	public void onSlimeSplit(SlimeSplitEvent event) {
		removeEntity(event.getEntity());
	}
	
	public void removeEntity(Entity entity) {
		if (entity instanceof LivingEntity) {
			Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
			if (commandMap.containsValue(entity)) {
				commandMap.values().removeAll(Collections.singleton(entity));
			}
		}
		
		Map<String, Entity> commandMap = CommandMapManager.getEntityCommandMap();
		if (commandMap.containsValue(entity)) {
			commandMap.values().removeAll(Collections.singleton(entity));
		}
	}
}
