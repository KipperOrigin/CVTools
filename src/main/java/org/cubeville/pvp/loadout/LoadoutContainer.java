package org.cubeville.pvp.loadout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SerializableAs("LoadoutContainer")
public class LoadoutContainer implements ConfigurationSerializable {

    Map<String, Inventory> inventories;
    private String loadoutName;

    @SuppressWarnings("unchecked")
    public LoadoutContainer (Map<String, Object> config) {
        inventories = new HashMap<>();
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
        return ret;
    }

    public LoadoutContainer(Player player, String title) {
        Inventory inv = Bukkit.createInventory(null, 54, title.toLowerCase() + ":main");
        inventories = new HashMap<>();
        inventories.put("main", inv);
        loadoutName = title;
        player.openInventory(inv);
    }
	
    public LoadoutContainer(String title, Map<String, Inventory> inventories) {
        loadoutName = title;
        this.inventories = inventories;
    }
	
    public Map<String, Inventory> getInventories() {
        return inventories;
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
	
    public void removeInventory(String teamName) {
		inventories.remove(teamName.toLowerCase());
	}
    
    public void createInventory(Player player, String teamName) {
        Inventory inv = Bukkit.createInventory(null, 54, loadoutName + ":" + teamName);
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
        return (inventories.containsKey(name));
    }

	 
}
