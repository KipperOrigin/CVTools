package org.cubeville.cvtools.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EventPlayerInteractEntity implements Listener {
    
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.isCancelled()) return;

		if (event.getHand() != EquipmentSlot.HAND) return;
		
		if (event.getRightClicked() instanceof Player) return;
		
		Entity entity = event.getRightClicked();
		Player player = event.getPlayer();
		CommandMap commandMap = CommandMapManager.primaryMap;
		
		if (commandMap.contains(player)) {
			event.setCancelled(true);
			
			if (commandMap.get(player) != null) return;
				
			commandMap.put(player, entity);
				
			if (entity.getCustomName() != null) {
				event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + entity.getCustomName() + "&a selected!"));
			} else {
				event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + entity.getName() + "&a selected!"));
			}
		}	
	}
}
