package org.cubeville.cvtools.events;

import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EventPlayerInteractEntity implements Listener {
    
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.isCancelled())
			return;

		if (event.getHand() != EquipmentSlot.HAND)
			return;
		
		Entity entity = event.getRightClicked();
		String name = event.getPlayer().getName();
		Map<String, LivingEntity> livingEntityCommandMap = CommandMapManager.getLivingEntityCommandMap();
		Map<String, Entity> entityCommandMap = CommandMapManager.getEntityCommandMap();
		
		if (entity instanceof LivingEntity) {
			if (livingEntityCommandMap.containsKey(name)) {
				
				event.setCancelled(true);
				
				if (livingEntityCommandMap.get(name) == entity)
					return;
				
				livingEntityCommandMap.put(name, (LivingEntity) entity);
				
				if (entity.getCustomName() != null)
					event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + entity.getCustomName() + "&a selected!"));
				else
					event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + entity.getName() + "&a selected!"));
			}
		}
			
		if (entityCommandMap.containsKey(name)) {

			event.setCancelled(true);
			
			if (entityCommandMap.get(name) == entity)
				return;
				
			entityCommandMap.put(name, (LivingEntity) entity);
				
			if (!livingEntityCommandMap.containsKey(name)) {
				if (entity.getCustomName() != null)
					event.getPlayer().sendMessage(Colorize.addColor("&aEntity &6" + entity.getCustomName() + "&a selected!"));
				else
					event.getPlayer().sendMessage(Colorize.addColor("&aEntity &6" + entity.getName() + "&a selected!"));
			}
		}		
	}
}
