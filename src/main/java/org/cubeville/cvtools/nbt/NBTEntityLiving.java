package org.cubeville.cvtools.nbt;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import net.minecraft.server.v1_9_R2.AttributeInstance;
import net.minecraft.server.v1_9_R2.AttributeMapBase;
import net.minecraft.server.v1_9_R2.EntityLiving;
import net.minecraft.server.v1_9_R2.NBTTagString;

public class NBTEntityLiving extends NBTEntity {

	EntityLiving livingEntity;
	LivingEntity entityLiving;
	
	public NBTEntityLiving(Entity entity) {
		super(entity);
		livingEntity = (EntityLiving) nmsEntity;
		entityLiving = (LivingEntity) entity;
	}
	
	@Override
	public EntityLiving getRawNMSEntity() {
		return livingEntity;
	}
	
	@Override
	public LivingEntity getRawEntity() {
		return entityLiving;
	}
	
	public Map<String, Double> getAttributes() {
		Map<String, Double> attributeList = new HashMap<>();
		AttributeMapBase base = livingEntity.getAttributeMap();
		for (AttributeInstance instance: base.a()) {
			getAttributeInstance(base, "poo");
			attributeList.put(instance.getAttribute().getName(), instance.getValue());
		}
		return attributeList;
	}
	
	public void addAttribute(AttributeType type, double value) {
		AttributeMapBase base = livingEntity.getAttributeMap();
		AttributeInstance instance = getAttributeInstance(base, type.toString());
		instance.setValue(value);
		System.out.println(instance.getAttribute().getName() + ":" + instance.getValue());
		base.a(instance);
	}
	
	public void setAbsorption(float f) {
		livingEntity.setAbsorptionHearts(f);
	}
	
	public AttributeInstance getAttributeInstance(AttributeMapBase base, String name) {
		for(AttributeInstance instance: base.a()) {
			System.out.println(name);
			System.out.println(instance.getAttribute().getName());
			System.out.println("_________________");
			if (instance.getAttribute().getName().equals(name)) {
				return instance;
			}
		}
		return null;
	}
	
	public static enum AttributeType {
		GENERIC_ARMOR(new NBTTagString("generic.armor")),
		GENERIC_ARMOR_TOUGHNESS(new NBTTagString("generic.armorToughness")),
		GENERIC_ATTACK_DAMAGE(new NBTTagString("generic.attackDamage")),
		GENERIC_ATTACKS_SPEED(new NBTTagString("generic.attackSpeed")),
		GENERIC_FOLLOW_RANGE(new NBTTagString("generic.followRange")),
		GENERIC_KNOCKBACK_RESISTANCE(new NBTTagString("generic.knockbackResistance")),
		GENERIC_LUCK(new NBTTagString("generic.luck")),
		GENERIC_MAX_HEALTH(new NBTTagString("generic.maxHealth")),
		GENERIC_MOVEMENT_SPEED(new NBTTagString("generic.movementSpeed")),
		HORSE_JUMP_STRENGTH(new NBTTagString("horse.jumpStrength"));
		
		private final NBTTagString type;
		
		private AttributeType(final NBTTagString type) {
			this.type = type;
		}
		
		public NBTTagString toTagString() {
			return type;
		}
		
		@Override
		public String toString() {
			return type.toString().replaceAll("\"", "");
		}
	}
}
