package org.cubeville.commons.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockGetter {
	
	public static List<Block> getBlocksInRadius(Location loc, int radius) {
		Location loc1 = new Location(loc.getWorld(), loc.getBlockX() - radius, loc.getBlockY() - radius, loc.getBlockZ() - radius);
		Location loc2 = new Location(loc.getWorld(), loc.getBlockX() + radius, loc.getBlockY() + radius, loc.getBlockZ() + radius);
		
		List<Block> blocks = getBlocksInCube(loc1, loc2);
		List<Block> newBlocks = new ArrayList<>();
		
		for(Block block: blocks) {
			if (block.getLocation().distance(loc) < radius)
				newBlocks.add(block);
		}
		
		return newBlocks;
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
	
	public static List<Block> getBlocksInRadiusByType(Location loc, int radius, Material... mats) {
		List<Block> blocks = getBlocksInRadius(loc, radius);
		
		return getBlocksByType(blocks, mats);
	}
	
	public static List<Block> getBlocksInCubeByType(Location loc1, Location loc2, Material... mats) {
		List<Block> blocks = getBlocksInCube(loc1, loc2);
		
		return getBlocksByType(blocks, mats);
	}
	
	public static List<Block> getBlocksByType(List<Block> blocks, Material... mats) {
		List<Block> newBlocks = new ArrayList<>();
		
		for(Block block: blocks) {
			boolean isMat = false;
			for(Material mat: mats) {
				isMat = false;
				if(block.getType() == mat)
					isMat = true;
			}
			if (isMat)
				newBlocks.add(block);
		}
		
		return newBlocks;
	}

}
