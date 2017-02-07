package org.cubeville.portal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@SerializableAs("Portal")
public class Portal implements ConfigurationSerializable
{
    private UUID world;
    private Vector minCorner;
    private Vector maxCorner;
    private Location targetLocation;
    private String message;
    private String name;
    
    public Portal(World world, Vector minCorner, Vector maxCorner, Location targetLocation, String message) {
        this.minCorner = minCorner;
        this.maxCorner = maxCorner;
        this.world = world.getUID();
        this.targetLocation = targetLocation;
        this.message = message;
    }

    public Portal(Map<String, Object> config) {
        minCorner = (Vector) config.get("minCorner");
        maxCorner = (Vector) config.get("maxCorner");
        world = UUID.fromString((String) config.get("world"));
        targetLocation = (Location) config.get("targetLocation");
        message = (String) config.get("message");
        name = (String) config.get("name");
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("minCorner", minCorner);
        ret.put("maxCorner", maxCorner);
        ret.put("world", world.toString());
        ret.put("targetLocation", targetLocation);
        ret.put("message", message);
        ret.put("name", name);
        return ret;
    }
    
    public boolean isPlayerInPortal(Player player) {
        Location loc = player.getLocation();
        if(!loc.getWorld().getUID().equals(world)) return false;
        Vector vloc = loc.toVector();
        return vloc.isInAABB(minCorner, maxCorner);
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public String getMessage() {
        return message;
    }
}
