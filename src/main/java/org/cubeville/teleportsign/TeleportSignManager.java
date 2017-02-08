package org.cubeville.teleportsign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("TeleportSignManager")
public class TeleportSignManager implements ConfigurationSerializable {
    
    private List<TeleportSign> signs;
    
    public TeleportSignManager() {
        signs = new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
	public TeleportSignManager(Map<String, Object> ret) {
    	signs = (List<TeleportSign>) ret.get("teleports");
    	if (signs == null) signs = new ArrayList<>();
    }
    
    public void addSign(String name) {
        if (!containsSign(name)) signs.add(new TeleportSign(name));
    }
    
    public void removeSign(String name) {
        signs.remove(new TeleportSign(name));
    }
    
    public boolean containsSign(String name) {
        for (TeleportSign sign: signs) {
            if (sign.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
    
    public TeleportSign getSign(String name) {
        for (TeleportSign sign: signs) {
            if (sign.getName().equalsIgnoreCase(name)) return sign;
        }
        return null;
    }

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> ret = new HashMap<>();
		ret.put("teleports", signs);
		return ret;
	}

}
