package org.cubeville.cvtools.commands.blocktools;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandParameterListEnum;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;

public class BlockFillUtil
{
    private static Random random = new Random();
    
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
    
}
