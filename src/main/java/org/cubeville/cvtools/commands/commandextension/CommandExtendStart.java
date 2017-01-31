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

public class CommandExtendStart extends Command {

    public CommandExtendStart() {
        super("extend start");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.secondaryMap;
        
        commandMap.put(player, baseParameters.get(0));
        return new CommandResponse("&aCommand extension successfully started!");
    }

}
