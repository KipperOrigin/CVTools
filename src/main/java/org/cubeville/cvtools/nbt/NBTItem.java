package org.cubeville.cvtools.nbt;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cubeville.cvtools.nbt.Attributes.Attribute;
import org.cubeville.cvtools.nbt.Attributes.AttributeType;
import org.cubeville.cvtools.nbt.Attributes.EquipmentSlot;

import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;

public class NBTItem {

	ItemMeta itemMeta = null;
	List<String> itemLore = null;
	ItemStack item = null;
	
	public NBTItem (ItemStack item) {
		this.item = item;
		itemMeta = item.getItemMeta();
		if (itemMeta.hasLore())
			itemLore = itemMeta.getLore();
		else
			itemLore = new ArrayList<String>();
	}
	
	public void addLore(String string) {
		itemLore.add(string);
	}
	
	public void removeLore(int i) {
		if (i > itemLore.size())
			i = itemLore.size();
		else if (i < 0)
			i = 0;
		
		itemLore.remove(i);
	}
	
	public void replaceLore(int i, String string) {
		if (i > itemLore.size())
			i = itemLore.size();
		else if (i < 0)
			i = 0;
		
		itemLore.set(i, string);
	}
	
	public void insertLore(int i, String string) {
		if (i > itemLore.size())
			i = itemLore.size();
		else if (i < 0)
			i = 0;
		
		itemLore.add(i, string);
	}
	
	public void clearLore() {
		itemLore.clear();
	}
	
	public void setType(Material mat) {
		item.setType(mat);
	}
	
	public void setDurability(short s) {
		item.setDurability(s);
	}
	
	public void setMaxDurability() {
		item.setDurability(item.getType().getMaxDurability());
	}
	
	public void setUnbreakable() {
		itemMeta.spigot().setUnbreakable(true);
	}
	
	public void setBreakable() {
		itemMeta.spigot().setUnbreakable(false);
	}
	
	public void setName(String string) {
		itemMeta.setDisplayName(string);
	}
	
	public void addFlags(ItemFlag... flags) {
		for (ItemFlag flag: flags)
			itemMeta.addItemFlags(flag);
	}
	
	public void removeFlags(ItemFlag... flags) {
		for (ItemFlag flag: flags)
			itemMeta.removeItemFlags(flag);
	}
	
	public void clearFlags() {
		List<ItemFlag> flags = new ArrayList<ItemFlag>();
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES))
			flags.add(ItemFlag.HIDE_ATTRIBUTES);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS))
			flags.add(ItemFlag.HIDE_DESTROYS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))
			flags.add(ItemFlag.HIDE_ENCHANTS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON))
			flags.add(ItemFlag.HIDE_PLACED_ON);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS))
			flags.add(ItemFlag.HIDE_POTION_EFFECTS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE))
			flags.add(ItemFlag.HIDE_UNBREAKABLE);
		for(ItemFlag flag: flags) {
			itemMeta.removeItemFlags(flag);
		}
	}
	
	public void addAllFlags() {
		List<ItemFlag> flags = new ArrayList<ItemFlag>();
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES))
			flags.add(ItemFlag.HIDE_ATTRIBUTES);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS))
			flags.add(ItemFlag.HIDE_DESTROYS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))
			flags.add(ItemFlag.HIDE_ENCHANTS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON))
			flags.add(ItemFlag.HIDE_PLACED_ON);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS))
			flags.add(ItemFlag.HIDE_POTION_EFFECTS);
		if (itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE))
			flags.add(ItemFlag.HIDE_UNBREAKABLE);
		for(ItemFlag flag: flags) {
			itemMeta.addItemFlags(flag);
		}
	}
	

	
	//Add Attributes to the top of the list rather than bottom
	public void addAttribute(String name, AttributeType type, double d, String slot) {
		Attributes attributes = new Attributes(item);
		attributes.add(Attribute.newBuilder().name(name).type(type).amount(d).slot(slot).build());
		item = attributes.getStack();
		itemMeta = item.getItemMeta();
	}
	
	//Removes Attributes from the bottom of the list rather than top
	public void removeAttribute(int i) {
		Attributes attributes = new Attributes(item);
		Attribute attribute = attributes.get(i); 
		attributes.remove(attribute);
		item = attributes.getStack();
		itemMeta = item.getItemMeta();
	}
	
	public double getAttributeAmountByName(AttributeType type, EquipmentSlot slot) {
	    Attributes attributes = new Attributes(item);
	    double amount = 0;
	    for (int i = 0; i < attributes.size(); i++) {
	        Attribute attribute = attributes.get(i);
	        if (attribute.getAttributeType() == type && attribute.getSlot() == slot.toString()) amount += attribute.getAmount();
	    }
	    return amount;
	}
	
	public void clearAttributes() {
		Attributes attributes = new Attributes(item);
		attributes.clear();
		item = attributes.getStack();
		itemMeta = item.getItemMeta();
	}
	
	public void addGlow() {
        net.minecraft.server.v1_9_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        item = CraftItemStack.asCraftMirror(nmsStack);
    }
	
    public void removeGlow() {
        net.minecraft.server.v1_9_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (nmsStack.hasTag()) {
            tag = nmsStack.getTag();
            tag.remove("ench");
            nmsStack.setTag(tag);
            item = CraftItemStack.asCraftMirror(nmsStack);
        }
    }
	
	public List<String> getAttributes() {
		List<String> attributeNames = new ArrayList<String>();
		Attributes attributes = new Attributes(item);
		
		for (int x = 0; x < attributes.size(); x++)
			attributeNames.add(attributes.get(x).getName());
		
		return attributeNames;
	}
	
	public List<String> getLore() {
		return itemLore;
	}
	
	public String getSpecificLore(int i) {
		if (i > itemLore.size())
			i = itemLore.size();
		else if (i < 0)
			i = 0;
		
		return itemLore.get(i);
	}
	
	public short getMaxDurability() {
		return item.getType().getMaxDurability();
	}
	
	public ItemStack asItemStack() {
		itemMeta.setLore(itemLore);
		item.setItemMeta(itemMeta);
		return item;
	}
	
	public boolean isUnbreakable() {
		return itemMeta.spigot().isUnbreakable();
	}

}
