package kipperorigin.simplenbt.events;



import org.bukkit.plugin.PluginManager;

import kipperorigin.simplenbt.Main;

public class EventManager {

    private Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }
    
    PluginManager pm;

	public void registerEvents() {
		pm = plugin.getServer().getPluginManager();
		
    	pm.registerEvents(new EventPlayerInteractEntity(), plugin);
    	pm.registerEvents(new EventPlayerQuit(), plugin);
    	pm.registerEvents(new EventEntityDeath(),plugin);
    }

}
