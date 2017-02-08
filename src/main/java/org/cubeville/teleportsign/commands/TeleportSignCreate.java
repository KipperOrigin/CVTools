package org.cubeville.teleportsign.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.CVTools;

public class TeleportSignCreate extends Command {

    public TeleportSignCreate() {
        super("create");
        addBaseParameter(new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        if (CVTools.getInstance().getTeleportSignManager().containsSign((String) baseParameters.get(0))) throw new CommandExecutionException("");
        CVTools.getInstance().getTeleportSignManager().addSign((String) baseParameters.get(0));
        return new CommandResponse("");
    }

}
