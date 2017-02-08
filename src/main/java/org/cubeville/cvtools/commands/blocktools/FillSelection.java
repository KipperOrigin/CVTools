package org.cubeville.cvtools.commands.blocktools;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

public class FillSelection extends BaseCommand
{
    public FillSelection() {
        super("fillselection");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterWeightedMaterialList()); // material to set
        addOptionalBaseParameter(new CommandParameterWeightedMaterialList()); // material to replace
        setPermission("cvtools.fillregion");
    }

    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        String group = (String) baseParameters.get(0);
        if(!SelectRegion.selectionExists(group)) throw new CommandExecutionException("No selection for selected group!");
        
        String worldName = SelectRegion.getWorldName(group);
        String regionName = SelectRegion.getRegionName(group);
        World world = Bukkit.getWorld(worldName);

        List<WeightedMaterial> materialList = (List<WeightedMaterial>) baseParameters.get(1);
        List<WeightedMaterial> replacedMaterialList = null;
        if(baseParameters.size() == 3) replacedMaterialList = (List<WeightedMaterial>) baseParameters.get(2);

        BlockToolUtil.fillRegion(world, regionName, materialList, replacedMaterialList);

        return null;
        
            

    }
}
