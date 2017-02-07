package org.cubeville.teleportsign.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.teleportsign.CommandParameterTeleportSign;
import org.cubeville.teleportsign.TeleportSign;

public class TeleportSignPlayer extends Command {

    public TeleportSignPlayer() {
        super("tp");
        addBaseParameter(new CommandParameterTeleportSign());
        addBaseParameter(new CommandParameterEnum(TeleportSign.Team.class));
        addParameter("player", true, new CommandParameterOnlinePlayer());
        addParameter("name", true, new CommandParameterString());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        Player tpPlayer = player;
        TeleportSign sign = (TeleportSign) baseParameters.get(0);
        if (parameters.containsKey("player")) tpPlayer = (Player) parameters.get("player");
        if (!sign.hasSignValues((TeleportSign.Team) baseParameters.get(0))) throw new CommandExecutionException("");
        
        if (parameters.containsKey("name")) {
           if (!sign.containsSignValue((TeleportSign.Team) baseParameters.get(0), (String) parameters.get("name"))) throw new CommandExecutionException("");
           sign.teleportPlayer(tpPlayer, (TeleportSign.Team) baseParameters.get(0), (String) parameters.get("name"));
        } else sign.teleportPlayer(tpPlayer, (TeleportSign.Team) baseParameters.get(0));
        return new CommandResponse("");
    }

}
