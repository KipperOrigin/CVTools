package kipperorigin.simplenbt.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import kipperorigin.simplenbt.commands.CommandMapManager;

public class EventPlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		removeKey(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		removeKey(event.getPlayer());
	}
	
	public void removeKey(Player player) {
		if (CommandMapManager.getBlockCommandMap().containsKey(player.getName()))
			CommandMapManager.getBlockCommandMap().remove(player.getName());
		if (CommandMapManager.getEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getEntityCommandMap().remove(player.getName());
		if (CommandMapManager.getLivingEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getLivingEntityCommandMap().remove(player.getName());
	}
}
