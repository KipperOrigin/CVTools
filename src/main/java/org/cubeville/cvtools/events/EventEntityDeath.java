package org.cubeville.cvtools.events;

import java.lang.reflect.Method;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class EventEntityDeath implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		NBTTagCompound compound = new NBTTagCompound();
		CraftEntity entity = (CraftEntity) event.getEntity();
		net.minecraft.server.v1_9_R2.Entity nms = entity.getHandle();
		Class<? extends Object> clazz = nms.getClass();
		Method[] methods = clazz.getMethods();
		for(Method method: methods) {
			if ((method.getName() == "b") && (method.getParameterTypes().length == 1) && (method.getParameterTypes()[0] == NBTTagCompound.class)) {
				try {
					method.setAccessible(true);
					method.invoke(nms, compound);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		removeEntity(event.getEntity());
	}
	
	@EventHandler
	public void onSlimeSplit(SlimeSplitEvent event) {
		removeEntity(event.getEntity());
	}
	
	public void removeEntity(Entity entity) {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (commandMap.contains(entity)) {
			for (String name: commandMap.getPlayersWithObject(entity)) {
				for (Player player: Bukkit.getOnlinePlayers()) {
					if (player.getName() == name) {
						player.sendMessage(Colorize.addColor("&cSelected entity has been killed! Entity deselected."));
					}
				}
			}
			commandMap.replaceValues(entity, null);
		}
	}
}
