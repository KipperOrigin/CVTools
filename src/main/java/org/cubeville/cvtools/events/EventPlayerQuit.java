package org.cubeville.cvtools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EventPlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		CommandMapManager.removePlayerFromAllMaps(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		CommandMapManager.removePlayerFromAllMaps(event.getPlayer());
	}
}
