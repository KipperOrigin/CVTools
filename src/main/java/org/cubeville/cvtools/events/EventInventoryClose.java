package org.cubeville.cvtools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.cubeville.cvtools.CVTools;

public class EventInventoryClose implements Listener {

     @EventHandler
     public void onInventoryClose(InventoryCloseEvent event) {
         if (CVTools.getInstance().getLoadoutManager().containsInventory(event.getInventory())) {
             CVTools.getInstance().getConfig().set("LoadoutManager", CVTools.getInstance().getLoadoutManager());
             CVTools.getInstance().saveConfig();
         }
     }
}
