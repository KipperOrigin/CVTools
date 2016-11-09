package kipperorigin.simplenbt;

import org.bukkit.plugin.java.JavaPlugin;

import kipperorigin.simplenbt.commands.NBTCommandExecutor;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		
		final NBTCommandExecutor ce = new NBTCommandExecutor();

		getCommand("snbt").setExecutor(ce);
	}
	
	public void onDisable() {
		// TO-DO
	}

}
