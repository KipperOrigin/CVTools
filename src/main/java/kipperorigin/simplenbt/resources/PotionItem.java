package kipperorigin.simplenbt.resources;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionItem {
	
	private PotionMeta potionMeta = null;
	private ItemStack itemStack = null;
	
	public PotionItem(ItemStack item) {
		if (item.getType() == Material.POTION || item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION) {
			potionMeta = (PotionMeta) item.getItemMeta();
			itemStack = item;
		}
	}
	
	public void setBasePotion(PotionType type) {
		PotionData data = new PotionData(type);
		potionMeta.setBasePotionData(data);
	}
	
	@SuppressWarnings("deprecation")
	public void setBasePotion(PotionEffectType type) {
		potionMeta.setMainEffect(type);
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
	
	public void setSplash() {
		itemStack.setType(Material.SPLASH_POTION);
	}
	
	public void setLingering() {
		itemStack.setType(Material.LINGERING_POTION);
	}
	
	public void setNormal() {
		itemStack.setType(Material.POTION);
	}
	
    public static class NBTPotionEffect {
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
    	
    	public boolean hasColor() {
    		if (potionEffect.getColor() == null)
    			return false;
    		else
    			return true;
    	}
	}
}
