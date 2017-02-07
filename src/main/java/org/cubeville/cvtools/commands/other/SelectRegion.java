package org.cubeville.cvtools.commands.other;

import java.util.HashMap;
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

public class SelectRegion extends BaseCommand
{
    public class RegionId {
        public RegionId(String world, String region) {
            this.region = region;
            this.world = world;
        }
        public String getWorld() { return world; };
        public String getRegion() { return region; };

        private String world;
        private String region;
    }
    
    static private Map<String, RegionId> groupSelection;
    
    public SelectRegion() {
        super("selectregion");
        addBaseParameter(new CommandParameterString()); // world name
        addBaseParameter(new CommandParameterString()); // region name
        addBaseParameter(new CommandParameterString()); // group name (spleef / lavarun / ...)
        setPermission("cvtools.fillregion");
        groupSelection = new HashMap<>();
    }

    public static String getWorldName(String group) {
        return groupSelection.get(group).getWorld();
    }

    public static String getRegionName(String group) {
        return groupSelection.get(group).getRegion();
    }
    
    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        World world = Bukkit.getWorld((String) baseParameters.get(0));
        String regionName = (String) baseParameters.get(1);
        String groupName = (String) baseParameters.get(2);

        if(world == null) throw new CommandExecutionException("World does not exist!");

        groupSelection.put(groupName, new RegionId((String) baseParameters.get(0), regionName));
        return null;
    }
}
