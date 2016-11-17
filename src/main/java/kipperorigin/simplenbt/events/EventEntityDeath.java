package kipperorigin.simplenbt.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;

import kipperorigin.simplenbt.resources.MobCommandMap;

public class EventEntityDeath implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (MobCommandMap.containsValue(event.getEntity())) 
			MobCommandMap.removeFromValue(event.getEntity());
	}
	
	@EventHandler
	public void onSlimeSplit(SlimeSplitEvent event) {
		if (MobCommandMap.containsValue(event.getEntity())) 
			MobCommandMap.removeFromValue(event.getEntity());
	}
}
