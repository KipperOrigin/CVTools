package org.cubeville.cvtools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.CVTools;

public class EventInventoryClose implements Listener {

     @EventHandler
     public void onInventoryClose(InventoryCloseEvent event) {
         if (CVTools.getInstance().getLoadoutManager().containsInventory(event.getInventory())) {
             CVTools.getInstance().getConfig().set("LoadoutManager", CVTools.getInstance().getLoadoutManager());
             CVTools.getInstance().saveConfig();
             String[] split = event.getInventory().getName().split(":");
             event.getPlayer().sendMessage(ColorUtils.addColor("&aLoadout &6" + split[0] + "&a:&6" + split[1] + " &asaved successfully!"));
         }
     }
}
