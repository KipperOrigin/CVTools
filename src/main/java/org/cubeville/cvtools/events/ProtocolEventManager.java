package org.cubeville.cvtools.events;

import org.cubeville.cvtools.CVTools;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ProtocolEventManager {
    
    private CVTools plugin;
    
    public ProtocolEventManager(CVTools plugin) {
        this.plugin = plugin;
    }
    
    public static ProtocolManager pm;
    
    public void registerEvents() {
        pm = ProtocolLibrary.getProtocolManager();
	
        // todo: not working, disabled until fixed: pm.addPacketListener(new EventSignPacketUpdate(plugin));
    }
    
    public static ProtocolManager getProtocolManager() {
        return pm;
    }
}
