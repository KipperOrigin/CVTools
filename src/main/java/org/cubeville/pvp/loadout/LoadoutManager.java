package org.cubeville.pvp.loadout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.CVTools;

@SerializableAs("LoadoutManager")
public class LoadoutManager implements ConfigurationSerializable {
	
    Map<String, LoadoutContainer> loadouts;
    Set<String> blacklist;
    CVTools plugin;
	
    public LoadoutManager() {
        loadouts = new HashMap<>();
        blacklist = new HashSet<>();
    }

    @SuppressWarnings("unchecked")
	public LoadoutManager(Map<String, Object> config) {
        System.out.println(config.get("loadouts").getClass().getName());
        loadouts = (Map<String, LoadoutContainer>) config.get("loadouts");
        blacklist = new HashSet<>();
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
    
    public void setManager(CVTools plugin) {
    	this.plugin = plugin;
    }
    
    public boolean blacklistContains(String name) {
    	return blacklist.contains(Bukkit.getPlayer(name).getName());
    }
    
    public void blacklistPlayer(String name, int i) {
    	if (Bukkit.getPlayer(name).isOnline())
    		Bukkit.getPlayer(name).sendMessage(Colorize.addColor("&cYou have been blacklisted from using loadouts!"));
    	blacklist.add(Bukkit.getPlayer(name).getName());
    	for (String line: blacklist) {
    		System.out.print(line);
    	}
    	Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
            	unblacklistPlayer(name);
        	}
    	}, i*1200);
    }
    
    public void unblacklistPlayer(String name) {
    	blacklist.remove(Bukkit.getPlayer(name).getName());
    	if (Bukkit.getPlayer(name).isOnline())
    		Bukkit.getPlayer(name).sendMessage(Colorize.addColor("&aYou have been unblacklisted from using loadouts!"));
    }
}
