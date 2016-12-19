package org.cubeville.cvtools.events;

import java.util.Collections;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

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
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (commandMap.contains(entity)) {
			commandMap.getRawMap().values().removeAll(Collections.singleton(entity));
		}
	}
}
