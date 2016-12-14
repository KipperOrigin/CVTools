package org.cubeville.cvtools.commands.simplenbt.entity;

import java.util.ArrayList;
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
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityInfo extends Command {

	public EntityInfo() {
		super("entity info");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		Map<String, Entity> commandMap = CommandMapManager.getEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		}
		
		Entity entity = commandMap.get(player.getName());
		List<String> info = new ArrayList<>();
		
		info.add("&aType&r: " + entity.getType().toString());
		info.add("&aCustom Name&r: " + entity.getCustomName());
		info.add("&aSilent&r: " + String.valueOf(entity.isSilent()));
		
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			info.add("&aAI&r: " + livingEntity.hasAI());
			info.add("&aInvulnerable&r: " + livingEntity.isInvulnerable());
			info.add("&aHealth&r: " + livingEntity.getHealth() + "&a/&r" + livingEntity.getMaxHealth());
			
			if (Ageable.class.isAssignableFrom(livingEntity.getClass())) {
				Ageable ageableEntity = (Ageable) livingEntity;
				info.add("&aAge&r: " + ageableEntity.getAge());
				info.add("&aAge Lock&r: " + ageableEntity.getAgeLock());
			}
			
			if (Tameable.class.isAssignableFrom(livingEntity.getClass())) {
				Tameable tameableEntity = (Tameable) livingEntity;
				info.add("&aTamed&r: " + tameableEntity.isTamed());
				if (tameableEntity.isTamed());
					if (tameableEntity.getOwner() != null)
						info.add("&aOwner&r: " + tameableEntity.getOwner().getName());
			}
			
			if (livingEntity instanceof Slime)
				info.add("&aHealth&r: " + ((Slime) livingEntity).getSize());
		}
		
		for (String message: info) {
			player.sendMessage(Colorize.addColor(message));
		}

		
	}
}
