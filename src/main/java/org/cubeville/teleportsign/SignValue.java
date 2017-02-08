package org.cubeville.teleportsign;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("SignValue")
public class SignValue implements ConfigurationSerializable {
    
    private String name;
    private Location location;
    
    public SignValue(String name, Location loc) {
        this.name = name;
        location = loc;
    }
    
    public SignValue(Map<String, Object> ret) {
        name = (String) ret.get("name");
        location = (Location) ret.get("location");
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLocation(Location loc) {
        location = loc;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("name", name);
        ret.put("location", location);
        return ret;
    }

}
