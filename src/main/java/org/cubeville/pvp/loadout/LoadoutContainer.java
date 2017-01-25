package org.cubeville.pvp.loadout;

import java.util.ArrayList;
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
import org.bukkit.inventory.ItemStack;

@SerializableAs("LoadoutContainer")
public class LoadoutContainer implements ConfigurationSerializable {

    Map<String, Inventory> inventories;
    Set<String> tags;
    private String loadoutName;

    @SuppressWarnings("unchecked")
    public LoadoutContainer (Map<String, Object> config) {
        inventories = new HashMap<>();
        tags = new HashSet<>();
        if (config.get("tags") != null)
        	tags.addAll((List<String>) config.get("tags"));
        loadoutName = (String) config.get("name");
        Map<String, List<ItemStack>> lists = (Map<String, List<ItemStack>>) config.get("loadouts");
        for(String invname: lists.keySet()) {
            Inventory inv = Bukkit.createInventory(null, 54, loadoutName + ":" + invname);
            for(int i = 0; i < lists.get(invname).size(); i++) {
                inv.setItem(i, lists.get(invname.toLowerCase()).get(i));
            }
            inventories.put(invname, inv);
        }
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        List<String> tagsList = new ArrayList<>();
        tagsList.addAll(tags);
        {
            Map<String, Object> inventoryMap = new HashMap<>();
            for(String invname: inventories.keySet()) {
                List<ItemStack> itemList = new ArrayList<>();
                for(int i = 0; i < inventories.get(invname).getSize(); i++) {
                    itemList.add(inventories.get(invname.toLowerCase()).getItem(i));
                }
                inventoryMap.put(invname, itemList);
            }
            ret.put("loadouts", inventoryMap);
        }
        ret.put("name", loadoutName);
        ret.put("tags", tagsList);
        return ret;
    }

    public LoadoutContainer(Player player, String title) {
        Inventory inv = Bukkit.createInventory(null, 54, title.toLowerCase() + ":main");
        inventories = new HashMap<>();
        tags = new HashSet<>();
        inventories.put("main", inv);
        loadoutName = title;
        player.openInventory(inv);
    }
	
    public LoadoutContainer(String title, Map<String, Inventory> inventories) {
        loadoutName = title.toLowerCase();
        this.inventories = inventories;
    }
	
    public Map<String, Inventory> getInventories() {
        return inventories;
    }
    
    public List<String> getInventoriesByName() {
    	List<String> inventoryNames = new ArrayList<>();
    	
    	for(Inventory inv: inventories.values()) {
    		inventoryNames.add(inv.getName().split(":")[1]);
    	}
    	
    	return inventoryNames;
    }
	
    public Inventory getMainInventory() {
        return inventories.get("main");
    }
	
    public Inventory getInventory(String teamName) {
        return inventories.get(teamName.toLowerCase());
    }
	
    public String getName() {
        return loadoutName;
    }
    
    public Set<String> getTags() {
    	return tags;
    }
	
    public void addTag(String tag) {
    	tags.add(tag.toLowerCase());
    }
    
    public void addTags(List<String> tags) {
    	for(String tag: tags) {
    		if (!this.tags.contains(tag)) {
    			this.tags.add(tag.toLowerCase());
    		}
    	}
    }
    
    public void removeTag(String tag) {
    	if (tags.contains(tag)) {
    		tags.remove(tag.toLowerCase());
    	}
    }
    
    public void clearTags() {
    	tags = new HashSet<>();
    }
    
    public void removeInventory(String teamName) {	
		inventories.remove(teamName.toLowerCase());
	}
    
    public void createInventory(Player player, String teamName) {
        Inventory inv = Bukkit.createInventory(null, 54, loadoutName + ":" + teamName.toLowerCase());
        inventories.put(teamName.toLowerCase(), inv);
        player.openInventory(inv);
    }
	
    public boolean editInventory(Player player, String teamName) {
        if (!inventories.containsKey(teamName.toLowerCase()))
            return false;
		
        Inventory inv = inventories.get(teamName.toLowerCase());
        player.openInventory(inv);
		
        return true;
    }
	
    public boolean containsInventory(String name) {
        return (inventories.containsKey(name.toLowerCase()));
    }
    
    public boolean contains(Inventory inventory) {
        return (inventories.containsValue(inventory));
    }
    
    public boolean containsTag(String tag) {
    	return tags.contains(tag.toLowerCase());
    }
    
    public boolean containsAllTags(List<String> tags) {
    	boolean contains = true;
    	for(String tag: tags) {
    		if(!this.containsTag(tag.toLowerCase())) {
    			contains = false;
    		}
    	}
    	
    	return contains;
    }

	 
}
