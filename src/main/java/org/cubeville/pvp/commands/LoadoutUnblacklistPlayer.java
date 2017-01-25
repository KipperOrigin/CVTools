package org.cubeville.pvp.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;

public class LoadoutUnblacklistPlayer extends Command {

    public LoadoutUnblacklistPlayer() {
        super("loadout unblacklist");
        addBaseParameter(new CommandParameterString());
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
                                   List<Object> baseParameters) throws CommandExecutionException {
        String name = (String) baseParameters.get(0);
        if (!CVTools.getInstance().getLoadoutManager().blacklistContains(name))
            throw new CommandExecutionException("&cPlayer is not currently blacklisted!");
        CVTools.getInstance().getLoadoutManager().unblacklistPlayer(name);
        return new CommandResponse("&aPlayer removed from blacklist!");
    }

}
