package org.cubeville.cvtools.nbt;

import org.bukkit.WeatherType;
import org.bukkit.entity.Entity;
import org.cubeville.cvtools.CVTools;

import net.minecraft.server.v1_9_R2.EntityPlayer;

public class NBTPlayer extends NBTEntityLiving {

	EntityPlayer player;
	CVTools plugin;
	
	public NBTPlayer(Entity entity) {
		super(entity);
		player = (EntityPlayer) nmsEntity;
	}
	
	public void setPlayerWeather(String weather) {
		player.setPlayerWeather(WeatherType.valueOf(weather), true);
	}
}
