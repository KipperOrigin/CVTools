package org.cubeville.cvtools.commands.simplenbt.block.mobspawner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockMobSpawner extends Command {
	
	public BlockMobSpawner() {
		super("block spawner");
		addParameter("entity", true, new CommandParameterEnum(EntityType.class));
		addParameter("delay", true, new CommandParameterInteger());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()).getState() instanceof CreatureSpawner)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return null;
		}
		
		CreatureSpawner spawner = (CreatureSpawner) commandMap.get(player.getName()).getState();
		
		if (parameters.containsKey("entity"))
			spawner.setSpawnedType((EntityType) parameters.get("entity"));
		if (parameters.containsKey("delay"))
			spawner.setDelay((int) parameters.get("delay"));
		
		spawner.update();
                return null;
	}
}
