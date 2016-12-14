package org.cubeville.commons.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class PlayerUtils {

	public static void removeAllPotionEffects(Player player) {
		player.removePotionEffect(PotionEffectType.ABSORPTION);
	    player.removePotionEffect(PotionEffectType.BLINDNESS);
	    player.removePotionEffect(PotionEffectType.CONFUSION);
	    player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	    player.removePotionEffect(PotionEffectType.FAST_DIGGING);
	    player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	    player.removePotionEffect(PotionEffectType.GLOWING);
	    player.removePotionEffect(PotionEffectType.HARM);
	    player.removePotionEffect(PotionEffectType.HEAL);
	    player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	    player.removePotionEffect(PotionEffectType.HUNGER);
	    player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    player.removePotionEffect(PotionEffectType.INVISIBILITY);
	    player.removePotionEffect(PotionEffectType.JUMP);
	    player.removePotionEffect(PotionEffectType.LEVITATION);
	    player.removePotionEffect(PotionEffectType.LUCK);
	    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
	    player.removePotionEffect(PotionEffectType.POISON);
	    player.removePotionEffect(PotionEffectType.REGENERATION);
	    player.removePotionEffect(PotionEffectType.SATURATION);
	    player.removePotionEffect(PotionEffectType.SLOW);
	    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	    player.removePotionEffect(PotionEffectType.SPEED);
	    player.removePotionEffect(PotionEffectType.UNLUCK);
	    player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	    player.removePotionEffect(PotionEffectType.WEAKNESS);
	    player.removePotionEffect(PotionEffectType.WITHER);
	}
}
