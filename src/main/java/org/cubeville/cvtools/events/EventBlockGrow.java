package org.cubeville.cvtools.events;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

public class EventBlockGrow implements Listener
{
    Set<UUID> noGrowthWorlds;

    EventBlockGrow() { // TODO: Read from config file
        noGrowthWorlds = new HashSet<>();
        noGrowthWorlds.add(UUID.fromString("f2d1566c-af98-4f1c-beb8-793a17deaf37"));
    }
    
    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        if(noGrowthWorlds.contains(event.getBlock().getLocation().getWorld().getUID())) {
            event.setCancelled(true);
        }
    }
}
