package org.cubeville.commons.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldedit.bukkit.selections.*;
import com.sk89q.worldedit.BlockVector;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class BlockUtils {
	
    public static List<Block> getBlocksInRadius(Location loc, int radius) {
        Location loc1 = new Location(loc.getWorld(), loc.getBlockX() - radius, loc.getBlockY() - radius, loc.getBlockZ() - radius);
        Location loc2 = new Location(loc.getWorld(), loc.getBlockX() + radius, loc.getBlockY() + radius, loc.getBlockZ() + radius);
		
        List<Block> blocks = getBlocksInCube(loc1, loc2);
        List<Block> newBlocks = new ArrayList<>();
		
        for(Block block: blocks) {
            if (block.getLocation().distance(loc) < radius)
                newBlocks.add(block);
        }

        return blocks;
    }
	
    public static List<Block> getBlocksInCube(Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<>();
		
        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {
                    blocks.add(new Location(loc1.getWorld(),x,y,z).getBlock());
                }
            }
        }
		
        return blocks;
    }

    public static List<Block> getBlocksInWESelection(Player player, int limit) {
        WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        Selection selection = worldEdit.getSelection(player);
        if(selection == null) throw new IllegalArgumentException("No region selected.");
        
        World world = selection.getWorld();
        Location min = selection.getMinimumPoint();
        Location max = selection.getMaximumPoint();
        List<Block> blocks = new ArrayList<>();

        int width = max.getBlockX() - min.getBlockX() + 1;
        int height = max.getBlockY() - min.getBlockY() + 1;
        int length = max.getBlockZ() - min.getBlockZ() + 1;
        if(width * length * height > limit) throw new IllegalArgumentException("Limit of " + limit + " reached!");

        for(int x = min.getBlockX(); x <= max.getBlockX(); x++) {
            for(int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                for(int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                    if(selection.contains(new Location(world, x, y, z))) {
                        blocks.add(world.getBlockAt(x, y, z));
                    }
                }
            }                
        }
        return blocks;
    }

    public static List<Block> getBlocksInWGRegion(Player player, String name, int limit) {
        World world = player.getWorld();
        
        WorldGuardPlugin worldGuard = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = worldGuard.getRegionManager(world);
        ProtectedRegion region = regionManager.getRegion(name);
        if(region == null) throw new IllegalArgumentException("No region found by that name!");
        BlockVector min = region.getMinimumPoint();
        BlockVector max = region.getMaximumPoint();
        List<Block> blocks = new ArrayList<>();

        int width = max.getBlockX() - min.getBlockX() + 1;
        int height = max.getBlockY() - min.getBlockY() + 1;
        int length = max.getBlockZ() - min.getBlockZ() + 1;
        if(width * length * height > limit) throw new IllegalArgumentException("Limit of " + limit + " reached!");
                                                
        for(int x = min.getBlockX(); x <= max.getBlockX(); x++) {
            for(int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                for(int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                    if(region.contains(new BlockVector(x, y, z))) {
                        blocks.add(world.getBlockAt(x, y, z));
                    }
                }
            }                
        }
        return blocks;
    }
    
    public static void setWESelection(Player player, World world, Vector pos1, Vector pos2) {
        WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        com.sk89q.worldedit.Vector wep1 = new com.sk89q.worldedit.Vector(pos1.getX(), pos1.getY(), pos1.getZ());
        com.sk89q.worldedit.Vector wep2 = new com.sk89q.worldedit.Vector(pos2.getX(), pos2.getY(), pos2.getZ());
        Selection selection = new CuboidSelection(world, wep1, wep2);
        worldEdit.setSelection(player, selection);
    }
    
    public static List<Block> getBlocksInRadiusByType(Location loc, int radius, Material... mats) {
        List<Block> blocks = getBlocksInRadius(loc, radius);
        return getBlocksByType(blocks, mats);
    }
	
    public static List<Block> getBlocksInCubeByType(Location loc1, Location loc2, Material... mats) {
        List<Block> blocks = getBlocksInCube(loc1, loc2);
        return getBlocksByType(blocks, mats);
    }

    public static List<Block> getBlocksInWESelectionByType(Player player, int limit, Material... mats) {
        List<Block> blocks = getBlocksInWESelection(player, limit);
        return getBlocksByType(blocks, mats);
    }

    public static List<Block> getBlocksInWGRegionByType(Player player, String name, int limit, Material... mats) {
        List<Block> blocks = getBlocksInWGRegion(player, name, limit);
        return getBlocksByType(blocks, mats);
    }
    
    public static List<Block> getBlocksByType(List<Block> blocks, Material... mats) {
        if(blocks == null) return null;
        
        List<Block> newBlocks = new ArrayList<>();
		
        for(Block block: blocks) {
            for(Material mat: mats) {
                if(block.getType() == mat) newBlocks.add(block);
            }
        }
        
        return newBlocks;
    }
    
    public static Block getNearestBlock(List<Block> blocks, Location loc) {
    	Block nearestBlock = null;
    	double distance = 10000;
    	
        if(blocks == null) return null;
    	
    	for(Block block: blocks) {
    		if (block.getLocation().distance(loc) < distance) {
    			nearestBlock = block;
    			distance = block.getLocation().distance(loc);
    		}
    	}
    	
		return nearestBlock;
    	
    }

}
