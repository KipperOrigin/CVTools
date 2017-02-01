package org.cubeville.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;
import org.cubeville.cvtools.nbt.NBTEntity;
import org.cubeville.cvtools.nbt.NBTEntityLiving;

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
	
    public static List<String> getInfo(Entity e, boolean attributes) {
        List<String> info = new ArrayList<>();
        NBTEntity entity = new NBTEntity(e);
		
        info.add("&aName&r: &6" + entity.getRawEntity().getName());
        if (entity.getRawEntity().getCustomName() != null) {
            info.add("&aCustom Name&r: " + entity.getRawEntity().getCustomName());
        }
        info.add("&aType&r: " + entity.getRawEntity().getType());
        info.add("&aSilent&r: " + entity.getRawEntity().isSilent());
        info.add("&aInvulnerable&r: " + entity.getRawEntity().isInvulnerable());
        info.add("&aInvisible&r: " + entity.getRawNMSEntity().isInvisible());
		
        if (e instanceof LivingEntity) {
            NBTEntityLiving livingEntity = new NBTEntityLiving(e);
            info.add("&aHealth&r: " + livingEntity.getRawEntity().getHealth() + "&a/&r" + livingEntity.getRawEntity().getMaxHealth());
            info.add("&aAbsorption&r: " + livingEntity.getRawNMSEntity().getAbsorptionHearts());
            info.add("&aArmor&r: " + livingEntity.getRawNMSEntity().getArmorStrength());

        }
		
        if (e instanceof Ageable) {
            if (((Ageable) e).isAdult()) {
                info.add("&aAge&r: Adult");
            } else {
                info.add("&aAge&r: Baby");
            }
            info.add("&aAge&r: " + ((Ageable) e).getAge());
        }
		
        if (e instanceof Tameable && ((Tameable) e).isTamed()) {
            info.add("&aOwner&r: " + ((Tameable) e).getOwner().getName());
        }
		
        if (attributes) {
            NBTEntityLiving livingEntity = new NBTEntityLiving(e);
            for(Map.Entry<String, Double> entry: livingEntity.getAttributes().entrySet()) {
                info.add("&a" + entry.getKey() + "&r: " + entry.getValue());
            }
        }
		
		
        return info;
    }
}
