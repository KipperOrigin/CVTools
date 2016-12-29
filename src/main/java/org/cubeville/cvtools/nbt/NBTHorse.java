package org.cubeville.cvtools.nbt;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import net.minecraft.server.v1_9_R2.EntityHorse;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;
import net.minecraft.server.v1_9_R2.NBTTagString;

public class NBTHorse extends NBTEntityLiving {

	EntityHorse horse;
	Horse entityHorse;
	
	public NBTHorse(Entity entity) {
		super(entity);
		entityHorse = (Horse) entity;
		horse = (EntityHorse) nmsEntity;
	}
	
	@Override
	public Horse getRawEntity() {
		return entityHorse;
	}
	
	@Override
	public EntityHorse getRawNMSEntity() {
		return horse;
	}
	
	public void unTame() {
		horse.attachedToPlayer = false;
		horse.setTame(false);
	}
}
