package org.cubeville.cvtools.commands.simplenbt.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;
import org.cubeville.cvtools.commands.CommandMapManager;

public class ObjectSelectNearest extends Command {

	public ObjectSelectNearest() {
		super("select nearest");
		addParameter("entity", true, new CommandParameterEnum(EntityType.class));
		addParameter("block", true, new CommandParameterEnum(Material.class));
		addParameter("radius", true, new CommandParameterInteger());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		int radius = 10;
		
		if (parameters.containsKey("radius")) {
			radius = (int) parameters.get("radius");
		}
		
		if (radius > 25) {
			throw new CommandExecutionException("&cA radius of &6" + radius + " &cis too large. Please use an integer below 25!");
		}
		
		CommandResponse cr = new CommandResponse();
		
		if (parameters.containsKey("entity") && !parameters.containsKey("block")) {
			Entity entity = getNearestEntity(player.getLocation(), getEntitiesByType(player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius), (EntityType) parameters.get("entity")));
			if (entity != null) {
				CommandMapManager.primaryMap.put(player, entity);
				if (entity.getCustomName() != null) {
					cr.setBaseMessage("&aEntity &6" + entity.getCustomName() + " &aselected at location " + entity.getLocation().getBlockX() + "," + entity.getLocation().getBlockY() + "," + entity.getLocation().getBlockZ());
				} else {
					cr.setBaseMessage("&aEntity &6" + entity.getName() + " &aselected at location " + entity.getLocation().getBlockX() + "," + entity.getLocation().getBlockY() + "," + entity.getLocation().getBlockZ());
				}
				
			} else {
				throw new CommandExecutionException("&cNo entities in radius of &6" + radius);
			}
		} else if (!parameters.containsKey("entity") && parameters.containsKey("block")) {
			Block block = BlockUtils.getNearestBlock(BlockUtils.getBlocksByType(BlockUtils.getBlocksInRadius(player.getLocation(), radius), (Material) parameters.get("block")), player.getLocation());
			if (block != null) {
				CommandMapManager.primaryMap.put(player, block);
				cr.setBaseMessage("&aEntity &6" + block.getType() + " &aselected at location " + block.getX() + "," + block.getY() + "," + block.getZ());
			} else {
				throw new CommandExecutionException("&cNo entities in radius of &6" + radius);
			}
		} else {
			throw new CommandExecutionException("&cPlease select a block or entity!");
		}
		
		
		
		return cr;
	}
	
	private List<Entity> getEntitiesByType(Collection<Entity> entityCollection, EntityType... types) {
		List<Entity> entities = new ArrayList<>();
		
		if (entityCollection == null) {
			return null;
		}
		
		for(Entity entity: entityCollection) {
			for(EntityType type: types) {
				if(entity.getType() == type) {
					entities.add(entity);
					break;
				}
			}
		}
		return entities;
		
	}
	
	
	private Entity getNearestEntity(Location loc, List<Entity> entities) {
    	Entity nearestEntity = null;
    	double distance = 10000;
    	
    	if (entities == null) {
    		return null;
    	}
    	
    	for(Entity entity: entities) {
    		if (nearestEntity == null) {
    			nearestEntity = entity;
    		}
    		if (distance == 10000) {
    			distance = entity.getLocation().distance(loc);
    		}
    		if (entity.getLocation().distance(loc) < distance) {
    			nearestEntity = entity;
    		}
    	}
    	
		return nearestEntity;
    	
    }

}
