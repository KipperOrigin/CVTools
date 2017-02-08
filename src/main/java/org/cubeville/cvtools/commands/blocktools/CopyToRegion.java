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

public class CopyToRegion extends BaseCommand
{
    public CopyToRegion() {
        super("copytoregion");
        addBaseParameter(new CommandParameterString()); // world of source region
        addBaseParameter(new CommandParameterString()); // name of source region
        addBaseParameter(new CommandParameterString()); // world of target region
        addBaseParameter(new CommandParameterString()); // name of target region
        setPermission("cvtools.fillregion");
    }

    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        World sourceWorld = Bukkit.getWorld((String) baseParameters.get(0));
        if(sourceWorld == null) throw new IllegalArgumentException("Source world does not exist!");
        String sourceRegion = (String) baseParameters.get(1);

        World targetWorld = Bukkit.getWorld((String) baseParameters.get(2));
        if(targetWorld == null) throw new IllegalArgumentException("Target world does not exist!");
        String targetRegion = (String) baseParameters.get(3);

        BlockToolUtil.copyRegion(sourceWorld, sourceRegion, targetWorld, targetRegion);

        return null;
    }
}
