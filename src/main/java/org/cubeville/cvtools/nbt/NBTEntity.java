package org.cubeville.cvtools.nbt;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class NBTEntity {

	net.minecraft.server.v1_9_R2.Entity nmsEntity;
	org.bukkit.entity.Entity entity;
	NBTTagCompound tag;
	
	public NBTEntity(org.bukkit.entity.Entity entity) {
		tag = new NBTTagCompound();
		this.entity = entity;
		nmsEntity = ((CraftEntity) entity).getHandle();
		tag = nmsEntity.e(tag);
	}
	
	public String getName() {
		if (nmsEntity.hasCustomName()) {
			return nmsEntity.getCustomName();
		} else {
			return nmsEntity.getName();
		}
	}
	
	public org.bukkit.entity.Entity getRawEntity() {
		return entity;
	}
	
	public net.minecraft.server.v1_9_R2.Entity getRawNMSEntity() {
		return nmsEntity;
	}
}
