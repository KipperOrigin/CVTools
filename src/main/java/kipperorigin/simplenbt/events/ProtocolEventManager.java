package kipperorigin.simplenbt.events;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import kipperorigin.simplenbt.Main;

public class ProtocolEventManager {

    private Main plugin;

    public ProtocolEventManager(Main plugin) {
        this.plugin = plugin;
    }
    
    public static ProtocolManager pm;

	public void registerEvents() {
		pm = ProtocolLibrary.getProtocolManager();
		
		pm.addPacketListener(new EventSignPacketUpdate(plugin));
    }
	
	public static ProtocolManager getProtocolManager() {
		return pm;
	}
}
