package org.cubeville.commons.utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class AdvancedSlots {
	
	public static ItemStack setEquipmentByName(LivingEntity entity, EquipmentSlot slot, ItemStack item, boolean chance, float c) {
		EntityEquipment equipment = entity.getEquipment();
		ItemStack replaceItem = null;
		
		if (slot == EquipmentSlot.HEAD)
			if (chance)
				equipment.setHelmetDropChance(c);
			else {
				replaceItem = equipment.getHelmet();
				equipment.setHelmet(item);
			}
		else if (slot == EquipmentSlot.CHEST)
			if (chance)
				equipment.setChestplateDropChance(c);
			else {
				replaceItem = equipment.getChestplate();
				equipment.setChestplate(item);
			}
		else if (slot == EquipmentSlot.HAND)
			if (chance)
				equipment.setItemInMainHandDropChance(c);
			else {
				replaceItem = equipment.getItemInMainHand();
				equipment.setItemInMainHand(item);
			}
		else if (slot == EquipmentSlot.OFF_HAND)
			if (chance)
				equipment.setItemInOffHandDropChance(c);
			else {
				replaceItem = equipment.getItemInOffHand();
				equipment.setItemInOffHand(item);
			}
		else if (slot == EquipmentSlot.LEGS)
			if (chance)
				equipment.setLeggingsDropChance(c);
			else {
				replaceItem = equipment.getLeggings();
				equipment.setLeggings(item);
			}
		else if (slot == EquipmentSlot.FEET)
			if (chance)
				equipment.setBootsDropChance(c);
			else {
				replaceItem = equipment.getBoots();
				equipment.setBoots(item);
			}
		
		return replaceItem;
	}
	
	public static boolean setAngleByName(ArmorStand stand, String string, EulerAngle angle) {
		boolean set = true;
		
		if (string.equalsIgnoreCase("body")) {
			stand.setBodyPose(angle);
		} else if (string.equals("head")) {
			stand.setHeadPose(angle);
		} else if (string.equalsIgnoreCase("left_arm")) {
			stand.setLeftArmPose(angle);
		} else if (string.equalsIgnoreCase("right_arm")) {
			stand.setRightArmPose(angle);
		} else if (string.equalsIgnoreCase("left_leg")) {
			stand.setLeftLegPose(angle);
		} else if (string.equalsIgnoreCase("right_leg")) {
			stand.setRightLegPose(angle);
		} else {
			set = false;
		}
		
		return set;
	}
}
