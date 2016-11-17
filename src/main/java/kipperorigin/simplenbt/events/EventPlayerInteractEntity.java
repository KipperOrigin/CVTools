package kipperorigin.simplenbt.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class EventPlayerInteractEntity implements Listener {
    
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.isCancelled())
			return;
		
		if (MobCommandMap.containsKey(event.getPlayer())) {
			if (event.getRightClicked() instanceof LivingEntity) {
				if (MobCommandMap.getValue(event.getPlayer()) == event.getRightClicked())
					return;
				
				MobCommandMap.addEventCommand(event.getPlayer(), (LivingEntity) event.getRightClicked());
				
				if (event.getRightClicked().getCustomName() != null)
					event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + event.getRightClicked().getCustomName() + "&a selected!"));
				else
					event.getPlayer().sendMessage(Colorize.addColor("&aMob &6" + event.getRightClicked().getName() + "&a selected!"));
				
			} else
				event.getPlayer().sendMessage(Colorize.addColor("&aPlease interact with a &6living entity&a!"));
			event.setCancelled(true);
		}
		
	}
}
