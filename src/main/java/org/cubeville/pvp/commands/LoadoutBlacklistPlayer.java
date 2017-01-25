package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;

public class LoadoutBlacklistPlayer extends Command {

    public LoadoutBlacklistPlayer() {
        super("loadout blacklist");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterInteger());
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        String name = (String) baseParameters.get(0);
        CVTools.getInstance().getLoadoutManager().blacklistPlayer(name, (int) baseParameters.get(1));
        return new CommandResponse("&aPlayer blacklisted!");
    }
}
