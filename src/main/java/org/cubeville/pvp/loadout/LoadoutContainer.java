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
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LoadoutContainer implements ConfigurationSerializable {

	Map<String, Inventory> inventories;
	private String loadoutName;

	
	public LoadoutContainer(Player player, String title) {
		Inventory inv = Bukkit.createInventory(null, 54, title + ":main");
		inventories = new HashMap<>();
		inventories.put("main", inv);
		loadoutName = title;
		player.sendMessage(inventories.get("main").getName());
		player.openInventory(inv);
	}
	
	public LoadoutContainer(String title, Map<String, Inventory> inventories) {
		loadoutName = title;
		this.inventories = inventories;
	}
	
	public Map<String, Inventory> getInventories() {
		return inventories;
	}
	
	public String getName() {
		return loadoutName;
	}
	
	public void createInventory(Player player, String teamName) {
		Inventory inv = Bukkit.createInventory(null, 54, loadoutName + ":" + teamName);
		inventories.put(teamName, inv);
		player.sendMessage(inv.getName());
		player.openInventory(inv);
	}
	
	public boolean editInventory(Player player, String teamName) {
		if (!inventories.containsKey(teamName))
			return false;
		
		Inventory inv = inventories.get(teamName);
		player.openInventory(inv);
		
		return true;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> loadoutMap = new LinkedHashMap<>();
		Iterator<Entry<String, Inventory>> it = inventories.entrySet().iterator();
		List<ItemStack> items = new ArrayList<>();
		
		loadoutMap.put("loadoutName", loadoutName);
		
		while (it.hasNext()) {
			Inventory inventory = it.next().getValue();
			for (int i = 0; i < inventory.getSize(); i++) {
				items.add(inventory.getItem(i));
			}
			loadoutMap.put(inventory.getName(), items);
		}
		
		return null;
	}
	 
	@SuppressWarnings("unchecked")
	public static LoadoutContainer deserialize(Map<String, Object> loadoutMap) {
		Map<String, Inventory> inventories = new HashMap<>();
		Iterator<Entry<String, Object>> it = loadoutMap.entrySet().iterator();
		
		if (!loadoutMap.containsKey("loadoutName"))
			return null;
		
		String loadoutName = (String) loadoutMap.get("loadoutName"); 
		
		while(it.hasNext()) {
			if (it.next().getKey() == "loadoutName")
				continue;
			Inventory inv = Bukkit.createInventory(null, 54, it.next().getKey().toString());
			List<ItemStack> items = (List<ItemStack>) it.next().getValue();
			for (ItemStack item: items) {
				int i = 0;
				inv.setItem(i, item);
				i++;
			}
		}
		
		return new LoadoutContainer(loadoutName, inventories);
	}
	
}
