package org.cubeville.cvtools.events;

import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.CVTools;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutManager;

public class EventSignChange implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onSignChange(SignChangeEvent event) {
    	String loAlias = "";
        LoadoutManager loadoutManager = CVTools.getInstance().getLoadoutManager();
        Player player = event.getPlayer();
        boolean exists = false;
		
        player.sendMessage("sign change!");
        
        for (String alias: EventPlayerInteract.LoadoutAliases) {
        	if (event.getLine(1).equalsIgnoreCase(alias)) {
        		loAlias = event.getLine(1).replaceAll("\\[", "").replaceAll("\\]","");
        		if (loadoutManager.contains(event.getLine(2))) {
        			LoadoutContainer lc = loadoutManager.getLoadoutByName(event.getLine(2));
        			if (lc.containsInventory(event.getLine(3))) exists = true;
        			else break;
        			if (!player.hasPermission("pvp.loadout.create")) {
        				player.sendMessage(Colorize.addColor("&cYou do not have permission to make loadout signs!"));
        				event.setCancelled(true);
        			}
        			break;
        		} 
        	}
        }
        if (exists) {
        	event.setLine(0, WordUtils.capitalizeFully(event.getLine(0)));
        	event.setLine(1, "[" + WordUtils.capitalizeFully(loAlias) + "]");
        	event.setLine(2, WordUtils.capitalizeFully(event.getLine(2)));
        	event.setLine(3, WordUtils.capitalizeFully(event.getLine(3)));
        	player.sendMessage(Colorize.addColor("&aLoadout sign created successfully!"));
        } else {
        	player.sendMessage(Colorize.addColor("&cLoadout &6" + event.getLine(2) + ":" + event.getLine(3) + " &cdoes not exist!"));
        	event.setCancelled(true);
        }
    }
}