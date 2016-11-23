package org.cubeville.cvtools.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.utils.Colorize;

public class EventPlayerInteract implements Listener {
    
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.isCancelled())
			return;
		
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		if (event.getHand() != EquipmentSlot.HAND)
			return;
		
		if (event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR)
			return;
		
		if (CommandMapManager.getBlockCommandMap().containsKey(event.getPlayer().getName())) {	
			if (CommandMapManager.getBlockCommandMap().get(event.getPlayer().getName()) == event.getClickedBlock()) 
				return;
			
			event.setCancelled(true);
			
			CommandMapManager.getBlockCommandMap().put(event.getPlayer().getName(), event.getClickedBlock());
			
			event.getPlayer().sendMessage(Colorize.addColor("&aBlock &6" + event.getClickedBlock().getType().name() + "&a selected!"));
		} 
	}
}
