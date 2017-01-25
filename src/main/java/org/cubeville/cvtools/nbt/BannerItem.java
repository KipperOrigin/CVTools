package org.cubeville.cvtools.nbt;

import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class BannerItem {

    private ItemStack itemStack = null;
    private BannerMeta bannerMeta = null;
	
    public BannerItem(ItemStack item) {
        if (item.getType() == Material.BANNER || item.getType() == Material.SHIELD) {
            itemStack = item;
            bannerMeta = (BannerMeta) item.getItemMeta();
        }
    }
	
    public void setBaseColor(DyeColor color) {
        bannerMeta.setBaseColor(color);
    }
	
    public void addPattern(Pattern pattern) {
        bannerMeta.addPattern(pattern);
    }
	
    public void addPattern(DyeColor color, PatternType type) {
        Pattern pattern = new Pattern(color, type);
        bannerMeta.addPattern(pattern);
    }
	
    public void removePattern(int i) {
        if (i > bannerMeta.getPatterns().size())
            i = bannerMeta.getPatterns().size();
        else if (i < 0)
            i = 0;
		
        bannerMeta.removePattern(i);
    }
	
    public void setPattern(int i, Pattern pattern) {
        if (i > bannerMeta.getPatterns().size())
            i = bannerMeta.getPatterns().size();
        else if (i < 0)
            i = 0;
		
        bannerMeta.setPattern(i, pattern);
    }
	
    public void setPattern(int i, DyeColor color, PatternType type) {
        bannerMeta.setPattern(i, new Pattern(color, type));
    }
	
    public void insertPattern(int i, Pattern pattern) {
        if (i > bannerMeta.getPatterns().size())
            i = bannerMeta.getPatterns().size();
        else if (i < 0)
            i = 0;
		
        List<Pattern> patterns = bannerMeta.getPatterns();
        patterns.add(i, pattern);
        bannerMeta.setPatterns(patterns);
    }
	
    public void insertPattern(int i, DyeColor color, PatternType type) {
        insertPattern(i, new Pattern(color, type));
    }
	
    public void clearPatterns() {
        int i = bannerMeta.numberOfPatterns();
        for (int x = 0; x < i; x++) {
            bannerMeta.removePattern(x);
        }
    }
	
    public void setShield() {
        itemStack.setType(Material.SHIELD);
    }
	
    public void setBanner() {
        itemStack.setType(Material.STANDING_BANNER);
    }
	
    public ItemStack asItemStack() {
        itemStack.setItemMeta(bannerMeta);
        return itemStack;
    }
	
}
