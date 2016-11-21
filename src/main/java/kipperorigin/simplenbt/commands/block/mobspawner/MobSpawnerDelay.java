package kipperorigin.simplenbt.commands.block.mobspawner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;
import org.cubeville.commons.CommandParameterEnum;

import kipperorigin.simplenbt.Main;
import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobSpawnerDelay extends Command {

	Main plugin;
	
	public MobSpawnerDelay() {
		super("block spawner delay");
		addBaseParameter(new CommandParameterEnum(EntityType.class));
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()).getState() instanceof CreatureSpawner)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		}
		
		CreatureSpawner spawner = (CreatureSpawner) commandMap.get(player.getName()).getState();

		spawner.setSpawnedType((EntityType) baseParameters.get(0));
		spawner.update();
	}
}
