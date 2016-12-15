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
import org.cubeville.cvtools.CVTools;

@SerializableAs("LoadoutManager")
public class LoadoutManager implements ConfigurationSerializable {
	
    Map<String, LoadoutContainer> loadouts;
	
    public LoadoutManager() {
        loadouts = new HashMap<>();
    }

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
        if (loadouts.containsKey(title.toLowerCase()))
            return true;
        else
            return false;
    }
	
    public LoadoutContainer getLoadout(String title) {
        if (loadouts.containsKey(title))
            return loadouts.get(title);
        else
            return null;
    }
	
    public List<String> getLoadoutNames() {
        List<String> loadoutList = new ArrayList<>(loadouts.keySet());
        Collections.sort(loadoutList);
        return loadoutList;
    }

}
