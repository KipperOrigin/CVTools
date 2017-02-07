package org.cubeville.cvtools.commands.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterListEnum;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.BlockUtils;

public class FillSelection extends BaseCommand
{
    public FillSelection() {
        super("fillselection");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterEnum(Material.class)); // material to set
        addParameter("data", true, new CommandParameterInteger()); // optional data for material
        addOptionalBaseParameter(new CommandParameterListEnum(Material.class)); // material to replace
        setPermission("cvtools.fillregion");        
    }

    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        String group = (String) baseParameters.get(0);

        String worldName = SelectRegion.getWorldName(group);
        if(worldName == null) throw new CommandExecutionException("No selection for selected group!");
        String regionName = SelectRegion.getRegionName(group);
        World world = Bukkit.getWorld(worldName);

        Material material = (Material) baseParameters.get(1);
        int data = -1;
        if(parameters.containsKey("data")) data = (int) parameters.get("data");
        List<Material> replace = null;
        if(baseParameters.size() > 3) replace = (List<Material>) baseParameters.get(2);

        List<Block> blocks;
        if(replace == null) blocks = BlockUtils.getBlocksInWGRegion(world, regionName, 25000);
        else blocks = BlockUtils.getBlocksInWGRegionByType(world, regionName, 25000, replace.toArray(new Material[0]));

        for(Block block: blocks) {
            block.setType(material, true);
            if(data >= 0 && data < 16) block.setData((byte) data);
        }

        return null;
        
            

    }
}
