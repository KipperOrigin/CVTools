package org.cubeville.pvp.loadout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.cubeville.cvtools.CVTools;

@SerializableAs("LoadoutManager")
public class LoadoutManager implements ConfigurationSerializable {
	
    Map<String, LoadoutContainer> loadouts;
	
    public LoadoutManager() {
        loadouts = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
	public LoadoutManager(Map<String, Object> config) {
        System.out.println(config.get("loadouts").getClass().getName());
        loadouts = (Map<String, LoadoutContainer>) config.get("loadouts");
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("loadouts", loadouts);
        return ret;
    }
    
    public boolean createLoadout(Player player, String title) {
        if (loadouts.containsKey(title.toLowerCase()))
            return false;
		
        loadouts.put(title.toLowerCase(), new LoadoutContainer(player, title));
		
        return true;
    }
	
    public boolean removeLoadout(String title) {
        if (loadouts.containsKey(title.toLowerCase())) {
            loadouts.remove(title.toLowerCase());
            return true;
        } else
            return false;
    }
	
    public boolean editLoadout(Player player, String title, String teamName) {
        if (!loadouts.containsKey(title.toLowerCase()))
            return false;
		
        LoadoutContainer loadout = loadouts.get(title.toLowerCase());
	
        return (loadout.editInventory(player, teamName.toLowerCase()));
    }
	
    public boolean contains(String title) {
        return (loadouts.containsKey(title.toLowerCase()));
    }
    
    public boolean containsInventory(Inventory inventory) {
        String[] split = inventory.getName().split(":");
        if (contains(split[0])) {
            return getLoadoutByName(split[0]).contains(inventory);
        } else {
            return false;
        }
    }
    
    public Inventory getInventory() {
        return null;
    }
	
    public LoadoutContainer getLoadoutByName(String title) {
        if (loadouts.containsKey(title.toLowerCase()))
            return loadouts.get(title.toLowerCase());
        else
            return null;
    }
	
    public List<String> getLoadoutNames() {
        List<String> loadoutList = new ArrayList<>(loadouts.keySet());
        Collections.sort(loadoutList);
        return loadoutList;
    }
    
    public List<String> getLoadoutNamesByTags(List<String> tags) {
    	List<String> loadoutList = new ArrayList<>();
    	
    	for(LoadoutContainer loadout: loadouts.values()) {
    		if (loadout.containsAllTags(tags))
    			loadoutList.add(loadout.getName());
    	}
    	
        Collections.sort(loadoutList);
    	return loadoutList;
    }
}
