package kipperorigin.simplenbt.resources;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionItem {
	
	private PotionMeta potionMeta = null;
	private ItemStack itemStack = null;
	
	public PotionItem(ItemStack item) {
		if (item.getType() == Material.POTION || item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION) {
			potionMeta = (PotionMeta) item.getItemMeta();
			itemStack = item;
		}
	}
	
	public void setBasePotion() {
		// TODO
	}
	
	public void addEffect(PotionEffect effect) {
		potionMeta.addCustomEffect(effect, true);
	}
	
	public void removeEffect(PotionEffectType type) {
		potionMeta.removeCustomEffect(type);
	}
	
	public void clearEffects() {
		potionMeta.clearCustomEffects();
	}
	
	public ItemStack asItemStack() {
		itemStack.setItemMeta(potionMeta);
		return itemStack;
	}
	
    public static class NBTPotionEffect {
		// TODO
    	
    	PotionEffect potionEffect = null;
    	
    	public void creatPotionEffect(PotionEffectType type, int duration, int amplifier) {
    		potionEffect = new PotionEffect(type, duration, amplifier);
    	}
    	
    	public void enableParticles() {
    		potionEffect = new PotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), true, true, potionEffect.getColor());
    	}
    	
    	public void disableParticles() {
    		potionEffect = new PotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), true, false, potionEffect.getColor());
    	}
    	
    	public void setColor(Color color) {
    		if (potionEffect.hasParticles())
    			potionEffect = new PotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), true, true, color);
    		else
    			potionEffect = new PotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), true, false, color);
    	}
    	
    	public PotionEffect getPotionEffect() {
    		return potionEffect;
    	}
	}
}
