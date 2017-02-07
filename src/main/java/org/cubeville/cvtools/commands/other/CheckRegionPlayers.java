package org.cubeville.cvtools.commands.other;

//       add search options for groups of entities

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class CheckRegionPlayers extends Command {

    public CheckRegionPlayers() {
        super("checkregionplayers");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {
        CommandResponse ret = new CommandResponse();
        World world = player.getWorld();
        WorldGuardPlugin worldGuard = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = worldGuard.getRegionManager(world);
        String name = (String) baseParameters.get(0);
        ProtectedRegion region = regionManager.getRegion(name);
        List<Player> players = world.getPlayers();
        String playerList = "";
        int playerCount = 0;
        for(Player p: players) {
            Location loc = p.getLocation();
            if(region.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                if(playerCount > 0) playerList += ", &e";
                playerList += p.getName();
                playerCount++;
            }
        }
        if(playerCount == 0) {
            ret.addMessage("&dNo players found in region &a" + name + "&d.");
        }
        else {
            ret.setBaseMessage(playerCount + " players in region &a" + name + "&e: " + playerList);
        }
        return ret;
    }

}
