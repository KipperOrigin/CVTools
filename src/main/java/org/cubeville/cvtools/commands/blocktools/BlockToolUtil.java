package org.cubeville.cvtools.commands.blocktools;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.command.CommandSender;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

import org.cubeville.commons.utils.BlockUtils;

public class BlockToolUtil
{
    private static Random random = new Random();
    private static WorldGuardPlugin worldGuard = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    
    public static void fillRegion(World world, String region, List<WeightedMaterial> materialList, List<WeightedMaterial> replacedMaterialList) {
        List<Block> blocks = BlockUtils.getBlocksInWGRegion(world, region, 50000);

        for(Block block: blocks) {
            if(isBlockOfType(block, replacedMaterialList)) {
                setBlock(block, materialList);
            }
        }
    }

    private static boolean isBlockOfType(Block block, List<WeightedMaterial> materialList) {
        if(materialList == null || materialList.size() == 0) return true;
        for(WeightedMaterial material: materialList) {
            if(material.getMaterial() == block.getType()) {
                if(material.getDataValue() == null || material.getDataValue() == block.getData()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void setBlock(Block block, List<WeightedMaterial> materialList) {
        if(materialList.size() == 1) {
            setBlock(block, materialList.get(0));
            return;
        }
        int percentageSum = 0;
        for(WeightedMaterial material: materialList) {
            percentageSum += material.getPercentage();
        }
        int rnd = random.nextInt(percentageSum);
        int percentageVal = 0;
        for(WeightedMaterial material: materialList) {
            if(rnd >= percentageVal && rnd < percentageVal + material.getPercentage()) {
                setBlock(block, material);
            }
            percentageVal += material.getPercentage();
        }
    }
    
    private static void setBlock(Block block, WeightedMaterial material) {
        block.setType(material.getMaterial());
        if(material.getDataValue() != null) {
            block.setData((byte) ((int) material.getDataValue()));
        }
        else {
            block.setData((byte) 0);
        }
    }

    public static void copyRegion(World sourceWorld, String sourceRegionName, World targetWorld, String targetRegionName) {
        ProtectedRegion sourceRegion = getRegion(sourceWorld, sourceRegionName);
        ProtectedRegion targetRegion = getRegion(targetWorld, targetRegionName);

        int sminx = sourceRegion.getMinimumPoint().getBlockX();
        int sminy = sourceRegion.getMinimumPoint().getBlockY();
        int sminz = sourceRegion.getMinimumPoint().getBlockZ();
        
        int ssizex = sourceRegion.getMaximumPoint().getBlockX() - sminx + 1;
        int ssizey = sourceRegion.getMaximumPoint().getBlockY() - sminy + 1;
        int ssizez = sourceRegion.getMaximumPoint().getBlockZ() - sminz + 1;

        int tminx = targetRegion.getMinimumPoint().getBlockX();
        int tminy = targetRegion.getMinimumPoint().getBlockY();
        int tminz = targetRegion.getMinimumPoint().getBlockZ();

        int tsizex = targetRegion.getMaximumPoint().getBlockX() - tminx + 1;
        int tsizey = targetRegion.getMaximumPoint().getBlockY() - tminy + 1;
        int tsizez = targetRegion.getMaximumPoint().getBlockZ() - tminz + 1;

        int sizex = Math.min(ssizex, tsizex);
        int sizey = Math.min(ssizey, tsizey);
        int sizez = Math.min(ssizez, tsizez);
        
        for(int x = 0; x < sizex; x++) {
            for(int y = 0; y < sizey; y++) {
                for(int z = 0; z < sizez; z++) {
                    Block targetBlock = targetWorld.getBlockAt(tminx + x, tminy + y, tminz + z);
                    Block sourceBlock = sourceWorld.getBlockAt(sminx + x, sminy + y, sminz + z);
                    targetBlock.setType(sourceBlock.getType());
                    BlockState data = sourceBlock.getState();
                    if(data instanceof Skull) {
                        targetBlock.setData(sourceBlock.getData()); // TODO: Should be changed with materialdata probably?
                        Skull sourceSkull = (Skull) data;
                        Skull targetSkull = (Skull) targetBlock.getState();
                        targetSkull.setSkullType(sourceSkull.getSkullType());
                        targetSkull.setRotation(sourceSkull.getRotation());
                        if(sourceSkull.getSkullType() == SkullType.PLAYER) {
                            targetSkull.setOwner(sourceSkull.getOwner());
                        }
                        targetSkull.update();
                    }
                    else {
                        targetBlock.setData(sourceBlock.getData());
                    }
                }
            }
        }
    }

    private static ProtectedRegion getRegion(World world, String regionName) {
        RegionManager regionManager = worldGuard.getRegionManager(world);
        ProtectedRegion region = regionManager.getRegion(regionName);
        if(region == null) throw new IllegalArgumentException("No region found by that name!");
        if(!(region instanceof ProtectedCuboidRegion)) throw new IllegalArgumentException("Region copy works only with cuboid regions!");
        return region;
    }
}
