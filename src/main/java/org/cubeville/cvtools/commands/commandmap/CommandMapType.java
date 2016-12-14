package org.cubeville.cvtools.commands.commandmap;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class CommandMapType extends Object {

	// Returns Specific Type Name
	public String getType() {
		if (isEntity())
			return ((Entity) this).getType().name();
		else if (isBlock())
			return ((Block) this).getType().name();
		else
			return this.getClass().toString() + "is an invalid Object!";
	}
	
	// Type Checks
	public boolean isEntity() {
		return (this instanceof Entity);
	}
	
	public boolean isEntityType(EntityType type) {
		if (isEntity()) {
			Entity entity = (Entity) this;
			if (type.getClass().isAssignableFrom(entity.getType().getClass()))
				return true;
			else
				return false;
		} else 
			return false;
					
	}
	
	public boolean isLivingEntity() {
		return (this instanceof LivingEntity);
	}
	
	public boolean isBlock() {	
		return (this instanceof Block);
	}
	
	public boolean isState(BlockState state) {
		if (this.isBlock()) {
			Block block = (Block) this;
			if (state.getClass().isAssignableFrom(block.getState().getClass()))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public Entity getAsEntity() {
		return (Entity) this;
	}
	
	public Entity getUnsafeEntity() throws CommandMapValueException {
		if (isEntity())
			return (Entity) this;
		else
			throw new CommandMapValueException("No entity selected!");
	}
}
