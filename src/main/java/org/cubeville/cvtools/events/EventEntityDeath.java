package org.cubeville.cvtools.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.cubeville.commons.utils.ColorUtils;
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
	
	@EventHandler
	public void onHangingBreak(HangingBreakEvent event) {
		removeEntity(event.getEntity());
	}
	
	public void removeEntity(Entity entity) {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (commandMap.contains(entity)) {
			for (String name: commandMap.getPlayersWithObject(entity)) {
				for (Player player: Bukkit.getOnlinePlayers()) {
					if (player.getName() == name) {
						player.sendMessage(ColorUtils.addColor("&cSelected entity has been killed! Entity deselected."));
					}
				}
			}
			commandMap.replaceValues(entity, null);
		}
	}
}
