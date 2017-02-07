package org.cubeville.portal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PortalManager
{
    private Plugin plugin;
    private Integer taskId;
    private List<Portal> portals;
    
    public PortalManager(Plugin plugin) {
        this.plugin = plugin;
        taskId = null;
        portals = (List<Portal>) plugin.getConfig().get("Portals");
        if(portals == null) {
            portals = new ArrayList<>();
        }
    }

    public void start() {
        if(taskId != null) plugin.getServer().getScheduler().cancelTask(taskId);
        
        Runnable runnable = new Runnable() {
                public void run() {
                    Collection<Player> players = (Collection<Player>) plugin.getServer().getOnlinePlayers();
                    for(Player player: players) {
                        for(Portal portal: portals) {
                            if(portal.isPlayerInPortal(player)) {
                                player.teleport(portal.getTargetLocation());
                                player.sendMessage(portal.getMessage());
                            }
                        }
                    }
                }
            };
        
        taskId = plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 30, 30).getTaskId();
    }
    
    public void stop() {
        plugin.getServer().getScheduler().cancelTasks(plugin);
    }
    
}
