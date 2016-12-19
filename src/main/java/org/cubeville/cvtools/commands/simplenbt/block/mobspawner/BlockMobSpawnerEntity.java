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
import org.cubeville.commons.utils.ObjectUtils;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.CommandMap;
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
		CommandMap commandMap = CommandMapManager.primaryMap;
		Block block;
		
		try {
			block = ObjectUtils.getObjectAsBlock(commandMap.get(player));
		} catch (RuntimeException e) {
			throw new CommandExecutionException("&cPlease select a &6mob spawner&a!");
		}
		
		if (!(block.getState() instanceof CreatureSpawner)) {
			throw new CommandExecutionException("&cPlease select a &6mob spawner&a!");
		}
		
		CreatureSpawner spawner = (CreatureSpawner) block.getState();

		spawner.setSpawnedType((EntityType) baseParameters.get(0));
		spawner.update();

		return new CommandResponse("&aMob spawner entity changed to &6" + ((EntityType) baseParameters.get(0)).name());
	}
}
