package org.cubeville.commons.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class ObjectUtils {

	public String getType(Object object) {
		if (object instanceof Entity)
			return ((Entity) object).getType().name();
		else if (object instanceof Block)
			return ((Block) object).getType().name();
		else
			return this.getClass().toString() + "is an invalid Object!";
	}
	
	public Entity getUnsafeEntity(Object object) {
		if (object instanceof Entity)
			return (Entity) object;
		else
			throw new RuntimeException("No entity selected!");
	}
	
	public static Block getObjectAsBlock(Object object) {
		if (object instanceof Block)
			return (Block) object;
		else
			throw new RuntimeException("Object is not a block!");
	}
}
