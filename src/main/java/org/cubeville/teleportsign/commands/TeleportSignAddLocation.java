package org.cubeville.teleportsign.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandParameterVector;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.teleportsign.CommandParameterTeleportSign;
import org.cubeville.teleportsign.SignValue;
import org.cubeville.teleportsign.TeleportSign;

public class TeleportSignAddLocation extends Command {

    public TeleportSignAddLocation() {
        super("add");
        addBaseParameter(new CommandParameterTeleportSign());
        addBaseParameter(new CommandParameterEnum(TeleportSign.Team.class));
        addBaseParameter(new CommandParameterString());
        addParameter("location", true, new CommandParameterVector());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        Location loc = player.getLocation();
        TeleportSign sign = (TeleportSign) baseParameters.get(0);
        if (sign.containsSignValue((TeleportSign.Team) baseParameters.get(1), (String) baseParameters.get(2))) throw new CommandExecutionException("");
        sign.addSignValue((TeleportSign.Team) baseParameters.get(1), new SignValue((String) baseParameters.get(2), loc));
        return new CommandResponse("");
    }
}
