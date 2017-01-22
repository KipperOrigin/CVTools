package org.cubeville.commons.utils;

import org.bukkit.enchantments.Enchantment;

import net.minecraft.server.v1_9_R2.NBTTagString;

public class EnchantmentUtils {

	public static enum EnchantmentType {
		ARROW_DAMAGE(Enchantment.ARROW_DAMAGE),
		ARROW_FIRE(Enchantment.ARROW_FIRE),
		ARROW_INFINITE(Enchantment.ARROW_INFINITE),
		ARROW_KNOCKBACK(Enchantment.ARROW_KNOCKBACK);
		
		private final Enchantment ench;
		
		private EnchantmentType(final Enchantment ench) {
			this.ench = ench;
		}
		
		public Enchantment toEnchantment() {
			return ench;
		}
	}
}
