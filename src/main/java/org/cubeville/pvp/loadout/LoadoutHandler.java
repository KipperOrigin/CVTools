package org.cubeville.pvp.loadout;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.utils.PlayerUtils;
import org.cubeville.cvtools.CVTools;

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
        if (inventory.getItem(49) != null)
            player.getInventory().setItemInOffHand(inventory.getItem(49));
        else
            player.getInventory().setItemInOffHand(baseInventory.getItem(49));
		
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
        return true;
    }
	
    public static boolean applyLoadoutFromSign(Player player, Sign sign) {		
        LoadoutContainer lc = CVTools.getInstance().getLoadoutManager().getLoadoutByName(sign.getLine(2));
		
        if (lc == null)
            return false;
		
        applyLoadoutToPlayer(player, lc, sign.getLine(3));
        return true;
    }
	
}
