package org.cubeville.cvtools.nbt;

import java.util.ArrayList;
import java.util.List;

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
	int multiplier = 1;
	
	public PotionItem(ItemStack item) {
	    if (item.getType() == Material.POTION || item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION || item.getType() == Material.TIPPED_ARROW) {
	        potionMeta = (PotionMeta) item.getItemMeta();
	        itemStack = item;
	    }
	    if (item.getType() == Material.LINGERING_POTION)
	    	multiplier = 4;
	    if (item.getType() == Material.TIPPED_ARROW)
	    	multiplier = 8;
	}
	
	public PotionEffectType getPotionEffectType(int i) {
		return potionMeta.getCustomEffects().get(i).getType();
	}
	
	public PotionEffect getPotionEffect(int i) {
		return potionMeta.getCustomEffects().get(i);
	}
	
	public PotionMeta getRawMeta() {
		return potionMeta;
	}
	
	public boolean isLength(int i) {
		if (potionMeta.getCustomEffects().size() < i-1 || i < 0)
			return false;
		else
			return true;
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
	
	public void removeEffect(int i) {
		List<PotionEffect> effects = potionMeta.getCustomEffects();
		List<PotionEffect> newEffects = new ArrayList<>();
		clearEffects();
		
		for(PotionEffect effect: effects) {
			if (!(newEffects.size() == i)) {
				newEffects.add(effect);
			}
		}
		
		for(PotionEffect effect: newEffects) {
			potionMeta.addCustomEffect(effect, true);
		}
	}
	
	public void clearEffects() {
		potionMeta.clearCustomEffects();
	}
	
	public ItemStack asItemStack() {
		itemStack.setItemMeta(potionMeta);
		return itemStack;
	}
	
	public void setSplash() {
		modifyDuration(1);
		itemStack.setType(Material.SPLASH_POTION);
	}
	
	public void setLingering() {
		modifyDuration(4);
		itemStack.setType(Material.LINGERING_POTION);
	}
	
	public void setNormal() {
		modifyDuration(1);
		itemStack.setType(Material.POTION);
	}
	
	public void setTippedArrow() {
		modifyDuration(8);
	    itemStack.setType(Material.TIPPED_ARROW);
	}
	
	public void modifyDuration(int i) {
		double finalMultiplier = Double.valueOf(i) / Double.valueOf(multiplier);
		System.out.println(finalMultiplier);
		for (int x = 0; x < potionMeta.getCustomEffects().size(); x ++) {
			PotionEffect startEffect = potionMeta.getCustomEffects().get(x);
			System.out.print(Math.round(startEffect.getDuration() * finalMultiplier));
			PotionEffect endEffect = new PotionEffect(startEffect.getType(), (int) Math.round(startEffect.getDuration() * finalMultiplier), startEffect.getAmplifier(), startEffect.isAmbient(), startEffect.hasParticles(), startEffect.getColor());
			potionMeta.addCustomEffect(endEffect, true);
		}
		multiplier = i;
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
