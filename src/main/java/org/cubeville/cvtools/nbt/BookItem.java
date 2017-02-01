package org.cubeville.cvtools.nbt;

// TODO: Why all that meta?

import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.cubeville.commons.utils.ColorUtils;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class BookItem {
	
    private net.minecraft.server.v1_9_R2.ItemStack item = null;
    private BookMeta meta = null;
    private NBTTagCompound tags = null;

    public BookItem(org.bukkit.inventory.ItemStack item) {
        if (item.getType() == Material.WRITTEN_BOOK) {
            this.item = CraftItemStack.asNMSCopy(item);
            meta = (BookMeta) item.getItemMeta();
            if (!this.item.hasTag())
                tags = new NBTTagCompound();
            else
                tags = this.item.getTag();
				
        } else
            return;
    }
	
    public List<String> getPages() {
        if (meta.hasPages())
            return meta.getPages();
        else return null;
    }
	
    public String getAuthor() {
        NBTTagCompound tags = item.getTag();
        return tags.getString("author");
    }
	
    public org.bukkit.inventory.ItemStack asItemStack() {
        item.setTag(tags);
        org.bukkit.inventory.ItemStack itemStack = CraftItemStack.asBukkitCopy(item);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
	
    public void setAuthor(String author) {
        meta.setAuthor(ColorUtils.addColor(author));
    }
	
    public void setTitle(String title) {		
        meta.setTitle(ColorUtils.addColor(title));
    }
	
    public void setPages(List<String> pages) {
        meta.setPages(pages);
    }

    public void colorizeBook() {
        if (meta.hasPages())
            meta.setPages(ColorUtils.addColor(meta.getPages()));
        this.setAuthor(meta.getAuthor());
        this.setTitle(meta.getTitle());
    }
	
    public void unsign() {
        meta.setAuthor(null);
        meta.setTitle(null);
        org.bukkit.inventory.ItemStack itemStack = CraftItemStack.asBukkitCopy(item);
        itemStack.setType(Material.BOOK_AND_QUILL);
        item = CraftItemStack.asNMSCopy(itemStack);
    }
	
    public boolean hasPages() {
        if (meta.hasPages())
            return true;
        else
            return false;
    }
	
}
