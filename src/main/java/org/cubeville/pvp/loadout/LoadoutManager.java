package org.cubeville.pvp.loadout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.cubeville.cvtools.CVTools;

public class LoadoutManager {
	
	private FileConfiguration loadoutsFC = null;
	private File loadoutsFile = null;
	
	Map<String, LoadoutContainer> loadouts;
	CVTools plugin;
	
	
	
	public LoadoutManager(CVTools plugin) {
		loadouts = new HashMap<>();
		this.plugin = plugin;
	}
	
	public boolean createLoadout(Player player, String title) {
		if (loadouts.containsKey(title))
			return false;
		
		player.sendMessage(title);
		
		loadouts.put(title, new LoadoutContainer(player, title));
		
		return true;
	}
	
	public boolean removeLoadout(String title) {
		if (loadouts.containsKey(title)) {
			loadouts.remove(title);
			return true;
		} else
			return false;
	}
	
	public boolean editLoadout(Player player, String title, String teamName) {
		if (!loadouts.containsKey(title))
			return false;
		
		LoadoutContainer loadout = loadouts.get(title);
	
		return (loadout.editInventory(player, teamName));
	}
	
	public boolean contains(String title) {
		if (loadouts.containsKey(title))
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
	
	public void reloadLoadouts() {
		if (loadoutsFile == null) {
			loadoutsFile = new File(plugin.getDataFolder(), "Loadouts.yml");
		}
		loadoutsFC = YamlConfiguration.loadConfiguration(loadoutsFile);
		for (String key: loadoutsFC.getKeys(false)) {
			LoadoutContainer loadout = (LoadoutContainer) loadouts.get(key);
			if (loadout == null)
				continue;
			loadouts.put(loadout.getName(), loadout);
		}
	}
	
	public FileConfiguration getLoadouts() {
		if (this.loadoutsFC == null) {
			reloadLoadouts();
		}
		return this.loadoutsFC;
	}
	
	public void saveLoadouts() {
		if (loadoutsFC == null || loadoutsFile == null || loadouts == null)
			return;
		try {
			for (Map.Entry<String, LoadoutContainer> entry: loadouts.entrySet()) {
				loadoutsFC.set(entry.getKey(),  entry.getValue());
			}
			try {
				getLoadouts().save(this.loadoutsFile);
			} catch (NullPointerException e) {
				System.out.println("Something is null :(");
				System.out.println(e);
			}
 		} catch (IOException e) {
 			plugin.getLogger().log(Level.SEVERE, "Could not save file to " + this.loadoutsFile, e);
 		}
	}
	
	
	
}
