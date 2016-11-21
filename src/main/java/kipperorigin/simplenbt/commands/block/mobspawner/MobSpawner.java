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
import org.cubeville.commons.CommandParameterInteger;
import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobSpawner extends Command {
	
	public MobSpawner() {
		super("block spawner");
		addParameter("entity", true, new CommandParameterEnum(EntityType.class));
		addParameter("delay", true, new CommandParameterInteger());
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
		
		if (parameters.containsKey("entity"))
			spawner.setSpawnedType((EntityType) parameters.get("entity"));
		if (parameters.containsKey("delay"))
			spawner.setDelay((int) parameters.get("delay"));
		
		spawner.update();
	}
}
