package kipperorigin.simplenbt.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import kipperorigin.simplenbt.resources.MobCommandMap;

public class EventPlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (MobCommandMap.containsKey(event.getPlayer())) {
			MobCommandMap.removeEventCommand(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		if (MobCommandMap.containsKey(event.getPlayer())) {
			MobCommandMap.removeEventCommand(event.getPlayer());
		}
	}
}
