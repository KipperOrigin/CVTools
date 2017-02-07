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

public class FillRegion extends BaseCommand
{
    Random random = new Random();
    
    public FillRegion() {
        super("fillregion");
        addBaseParameter(new CommandParameterString()); // world name
        addBaseParameter(new CommandParameterString()); // region name
        addBaseParameter(new CommandParameterWeightedMaterialList()); // materials to set
        addOptionalBaseParameter(new CommandParameterWeightedMaterialList()); // materials to replace
        setPermission("cvtools.fillregion");
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        World world = Bukkit.getWorld((String) baseParameters.get(0));
        if(world == null) throw new CommandExecutionException("World does not exist!");
        
        String regionName = (String) baseParameters.get(1);

        List<WeightedMaterial> materialList = (List<WeightedMaterial>) baseParameters.get(2);
        List<WeightedMaterial> replacedMaterialList = null;
        if(baseParameters.size() == 4) replacedMaterialList = (List<WeightedMaterial>) baseParameters.get(3);

        BlockFillUtil.fillRegion(world, regionName, materialList, replacedMaterialList);

        return null;
    }


}
