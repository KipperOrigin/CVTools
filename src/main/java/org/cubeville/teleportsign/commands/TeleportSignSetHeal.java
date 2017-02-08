package org.cubeville.teleportsign.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.teleportsign.CommandParameterTeleportSign;
import org.cubeville.teleportsign.TeleportSign;

public class TeleportSignSetHeal extends Command {

    public TeleportSignSetHeal() {
        super("set heal");
        addBaseParameter(new CommandParameterTeleportSign());
        addBaseParameter(new CommandParameterEnum(TeleportSign.Team.class));
        addBaseParameter(new CommandParameterBoolean());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
            List<Object> baseParameters) throws CommandExecutionException {
        TeleportSign sign = (TeleportSign) baseParameters.get(0);
        sign.getSignProperties((TeleportSign.Team) baseParameters.get(1)).setHeal((boolean) baseParameters.get(2));
        return new CommandResponse("");
    }
}
