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

public class CopyToSelection extends BaseCommand
{

    public CopyToSelection() {
        super("copytoselection");
        addBaseParameter(new CommandParameterString()); // selection
        addBaseParameter(new CommandParameterString()); // world of source region
        addBaseParameter(new CommandParameterString()); // name of source region
        setPermission("cvtools.fillregion");
    }

    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        String group = (String) baseParameters.get(0);
        if(!SelectRegion.selectionExists(group)) throw new CommandExecutionException("No selection for this group!");

        World sourceWorld = Bukkit.getWorld((String) baseParameters.get(1));
        if(sourceWorld == null) throw new IllegalArgumentException("Source world does not exist!");

        World targetWorld = Bukkit.getWorld(SelectRegion.getWorldName(group));
        if(targetWorld == null) throw new IllegalArgumentException("Target world does not exist!");

        BlockToolUtil.copyRegion(sourceWorld, (String) baseParameters.get(2), targetWorld, SelectRegion.getRegionName(group));

        return null;
    }

}
