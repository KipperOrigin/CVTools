package org.cubeville.cvtools.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.pvp.loadout.LoadoutHandler;

public class EventPlayerInteract implements Listener {
    
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
		
        if (CommandMapManager.primaryMap.contains(player)) {
            if (CommandMapManager.primaryMap.get(player) != null) return;
			
            event.setCancelled(true);
			
            if (event.getClickedBlock() == null) {
                return;
            }
            if (CommandMapManager.primaryMap.get(player) == event.getClickedBlock()) {
                player.sendMessage(ColorUtils.addColor("&cBlock already selected!"));
                return;
            }
            if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;	
            }
            if (event.getHand() != EquipmentSlot.HAND) {
                return;
            }
			
            CommandMapManager.primaryMap.put(player, event.getClickedBlock());
            player.sendMessage(ColorUtils.addColor("&aBlock &6" + event.getClickedBlock().getType().name() + "&a selected!"));
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerInteractSign(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.WALL_SIGN) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Sign sign = (Sign) event.getClickedBlock().getState();
        if(sign.getLine(1).length() == 0 || sign.getLine(1).charAt(0) != '[') return;

        for (String lString: LoadoutAliases) {
            if (sign.getLine(1).equalsIgnoreCase(lString)) {
                if (CVTools.getInstance().getLoadoutManager().blacklistContains(event.getPlayer().getName())) {
                    event.getPlayer().sendMessage(ColorUtils.addColor("&cYou are currently blacklisted from using loadouts!"));
                    return;
                }
                LoadoutHandler.applyLoadoutFromSign(event.getPlayer(), sign);
                event.setCancelled(true);
                return;
            }
        }
    }

	
    public static List<String> LoadoutAliases = new ArrayList<>(Arrays.asList("[load-out]","[kit]","[class]"));
}
