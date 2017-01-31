package org.cubeville.pvp.loadout;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.utils.PlayerUtils;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.nbt.Attributes.AttributeType;
import org.cubeville.cvtools.nbt.Attributes.EquipmentSlot;
import org.cubeville.cvtools.nbt.NBTItem;

public class LoadoutHandler {
	
    public static boolean applyLoadoutToPlayer(Player player, LoadoutContainer lc, String name) {
        if (lc.getInventory(name) == null)
            return false;
		
        Inventory inventory = lc.getInventory(name);
        Inventory baseInventory = lc.getMainInventory();

        //Update Player
        player.getInventory().clear();
        PlayerUtils.removeAllPotionEffects(player);
		
        //Hotbar & Offhand
        if (inventory.getItem(49) != null) player.getInventory().setItemInOffHand(inventory.getItem(49));
        else player.getInventory().setItemInOffHand(baseInventory.getItem(49));
		
        for (int i = 0; i < 9; i++) {
            ItemStack item = inventory.getItem(i);
			
            if (item == null)
                item = baseInventory.getItem(i);

            if(item != null) {
                System.out.println("Set hotbar item " + i + " to " + item.getType());
            }
            else {
                System.out.println("Hotbar item " + i + " is null.");
            }
            player.getInventory().setItem(i, item);
        }
		
        //Armor
        for (int i = 36; i < 40; i++) {
            ItemStack item = inventory.getItem(i+9);
			
            if (item == null)
                item = baseInventory.getItem(i+9);
			
            player.getInventory().setItem(i, item);
        }
		
        //Inventory Contents
        for (int i = 9; i < 36; i++) {
            ItemStack item = inventory.getItem(i);
			
            if (item == null)
                item = baseInventory.getItem(i);
			
            player.getInventory().setItem(i, item);
        }
		
        //Update Inventory
        player.updateInventory();
        player.setHealth(20);
        return true;
    }
	
    public static boolean applyLoadoutFromSign(Player player, Sign sign) {		
        LoadoutContainer lc = CVTools.getInstance().getLoadoutManager().getLoadoutByName(sign.getLine(2));
		
        if (lc == null)
            return false;
		
        applyLoadoutToPlayer(player, lc, sign.getLine(3));
        return true;
    }
    
    public static void cloneInventoryToLoadout(Player player, LoadoutContainer lc, String team) {
        Inventory inventory;
        if (team.equalsIgnoreCase("main")) {
            inventory = lc.getMainInventory();
        } else {
            return;
        }
        inventory.clear();
        inventory.setItem(49, player.getInventory().getItemInOffHand());
        for (int i = 0; i < 36; i++) inventory.setItem(i, player.getInventory().getItem(i));
        for (int i = 36; i < 40; i++) inventory.setItem(i+9, player.getInventory().getItem(i));
        CVTools.getInstance().getConfig().set("LoadoutManager", CVTools.getInstance().getLoadoutManager());
        CVTools.getInstance().saveConfig();
    }
    
    public double getHealthOfArmorContents(Player player) {
        double health = player.getMaxHealth();
        NBTItem helmet = new NBTItem(player.getEquipment().getHelmet());
        health += helmet.getAttributeAmountByName(AttributeType.GENERIC_MAX_HEALTH, EquipmentSlot.HEAD);
        NBTItem chestplate = new NBTItem(player.getEquipment().getChestplate());
        health += chestplate.getAttributeAmountByName(AttributeType.GENERIC_MAX_HEALTH, EquipmentSlot.CHEST);
        NBTItem leggings = new NBTItem(player.getEquipment().getLeggings());
        health += leggings.getAttributeAmountByName(AttributeType.GENERIC_MAX_HEALTH, EquipmentSlot.LEGS);
        NBTItem boots = new NBTItem( player.getEquipment().getBoots());
        health += boots.getAttributeAmountByName(AttributeType.GENERIC_MAX_HEALTH, EquipmentSlot.FEET);
        return health;
    }
	
}
