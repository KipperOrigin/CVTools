package org.cubeville.cvtools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.CVTools;
import org.cubeville.pvp.loadout.LoadoutContainer;
import org.cubeville.pvp.loadout.LoadoutManager;

public class EventSignChange implements Listener {
    
    @EventHandler (priority = EventPriority.MONITOR)
    public void onSignChange(SignChangeEvent event) {
        String[] lines = event.getLines();

        if(lines[1].length() == 0 || lines[1].charAt(0) != '[') return;

        {
            boolean found = false;
            for (String alias: EventPlayerInteract.LoadoutAliases) {
                if (lines[1].equalsIgnoreCase(alias)) {
                    found = true;
                }            
            }
            if(found == false) return;
        }

        Player player = event.getPlayer();

        if (!(player.hasPermission("cvpvp.admin") || player.hasPermission("snbt.loadout.commands"))) {
            player.sendMessage(ColorUtils.addColor("&cYou do not have permission to make loadout signs!"));
            event.setCancelled(true);
            return;
        }

        LoadoutManager loadoutManager = CVTools.getInstance().getLoadoutManager();
        LoadoutContainer lc = loadoutManager.getLoadoutByName(lines[2]);
        if(lc == null) {
            player.sendMessage(ColorUtils.addColor("&cLoadout &6" + event.getLine(2) + " &cdoes not exist!"));
            event.setCancelled(true);
            return;
        }

        if(!lc.containsInventory(lines[3])) {
            player.sendMessage(ColorUtils.addColor("&cTag &6" + lines[2] + ":" + lines[3] + " &cdoes not exist!"));
            event.setCancelled(true);
            return;
        }

        player.sendMessage(ColorUtils.addColor("&aLoadout sign created successfully!"));

    }
}
