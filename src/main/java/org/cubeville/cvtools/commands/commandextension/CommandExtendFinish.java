package org.cubeville.cvtools.commands.commandextension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class CommandExtendFinish extends Command {

    public CommandExtendFinish() {
        super("extend finish");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.secondaryMap;
        
        if (!(commandMap.contains(player) && commandMap.get(player) instanceof String)) throw new CommandExecutionException("");
        
        String lastString = (String) commandMap.get(player);
        if (lastString.endsWith("\\")) lastString = lastString.substring(0, lastString.length() - 1);
        else lastString += " ";
        player.performCommand(lastString + baseParameters.get(0));
        return new CommandResponse("&aCommand extension successfully executed!");
    }

}

