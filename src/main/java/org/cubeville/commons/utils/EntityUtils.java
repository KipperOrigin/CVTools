package org.cubeville.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityUtils {
	
	public static List<Entity> getEntitiesByType(Collection<Entity> entityCollection, EntityType... types) {
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
	
	public static Entity getNearestEntity(Location loc, List<Entity> entities) {
    	Entity nearestEntity = null;
    	double distance = 10000;
    	
    	if (entities == null) {
    		return null;
    	}
    	
    	for(Entity entity: entities) {
    		if (entity.getLocation().distance(loc) < distance) {
    			nearestEntity = entity;
    			distance = entity.getLocation().distance(loc);
    		}
    	}
    	
		return nearestEntity;
    	
    }
}
