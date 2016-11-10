package kipperorigin.simplenbt.resources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		itemLore.remove(i);
	}
	
	public void replaceLore(int i, String string) {
		itemLore.set(i, string);
	}
	
	public void clearLore() {
		itemLore.clear();
	}
	
	public void setType(Material mat) {
		if (item == null) {
			System.out.println("item is null");
			return;
		}
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
	
	public List<String> getLore() {
		return itemLore;
	}
	
	public String getSpecificLore(int i) {
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
