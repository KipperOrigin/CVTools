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
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.CommandMapManager;

public class BlockMobSpawnerEntity extends Command {

	CVTools plugin;
	
	public BlockMobSpawnerEntity() {
		super("block spawner entity");
		addBaseParameter(new CommandParameterEnum(EntityType.class));
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			return new CommandResponse("&cPlease select a &6sign&c!");
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()).getState() instanceof CreatureSpawner)) {
			return new CommandResponse("&cPlease select a &6sign&c!");
		}
		
		CreatureSpawner spawner = (CreatureSpawner) commandMap.get(player.getName()).getState();

		spawner.setSpawnedType((EntityType) baseParameters.get(0));
		spawner.update();

                return null;
	}
}
