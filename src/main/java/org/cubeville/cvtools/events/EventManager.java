package org.cubeville.cvtools.events;



import org.bukkit.plugin.PluginManager;
import org.cubeville.cvtools.CVTools;

public class EventManager {

    private CVTools plugin;

    public EventManager(CVTools plugin) {
        this.plugin = plugin;
    }
    
    PluginManager pm;

    public void registerEvents() {
        pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new EventBlockRemoval(), plugin);
        pm.registerEvents(new EventEntityDeath(), plugin);
    	pm.registerEvents(new EventInventoryClose(), plugin);
    	pm.registerEvents(new EventPlayerInteract(), plugin);
    	pm.registerEvents(new EventPlayerInteractEntity(), plugin);
    	pm.registerEvents(new EventPlayerQuit(), plugin);
    	pm.registerEvents(new EventSignChange(), plugin);
    }
}
