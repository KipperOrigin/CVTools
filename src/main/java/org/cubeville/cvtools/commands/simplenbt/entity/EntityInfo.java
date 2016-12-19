package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityInfo extends Command {

	public EntityInfo() {
		super("entity info");
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select an &6entity&c!");
		} else if (!(commandMap.get(player) instanceof Entity)) {
			throw new CommandExecutionException("&cPlease select an &6entity&c!");
		}
		
		Entity entity = (Entity) commandMap.get(player);
		
		CommandResponse cr = new CommandResponse();
		
		cr.setBaseMessage("&aType&r: " + entity.getType().toString());
		cr.addMessage("&aCustom Name&r: " + entity.getCustomName());
		cr.addMessage("&aSilent&r: " + String.valueOf(entity.isSilent()));
		
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			cr.addMessage("&aAI&r: " + livingEntity.hasAI());
			cr.addMessage("&aInvulnerable&r: " + livingEntity.isInvulnerable());
			cr.addMessage("&aHealth&r: " + livingEntity.getHealth() + "&a/&r" + livingEntity.getMaxHealth());
			
			if (Ageable.class.isAssignableFrom(livingEntity.getClass())) {
				Ageable ageableEntity = (Ageable) livingEntity;
				cr.addMessage("&aAge&r: " + ageableEntity.getAge());
				cr.addMessage("&aAge Lock&r: " + ageableEntity.getAgeLock());
			}
			
			if (Tameable.class.isAssignableFrom(livingEntity.getClass())) {
				Tameable tameableEntity = (Tameable) livingEntity;
				cr.addMessage("&aTamed&r: " + tameableEntity.isTamed());
				if (tameableEntity.isTamed());
					if (tameableEntity.getOwner() != null)
						cr.addMessage("&aOwner&r: " + tameableEntity.getOwner().getName());
			}
			
			if (livingEntity instanceof Slime)
				cr.addMessage("&aHealth&r: " + ((Slime) livingEntity).getSize());
		}
         
		return cr;
	}
}
