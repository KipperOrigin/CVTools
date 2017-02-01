package org.cubeville.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityRemove extends Command {

    public EntityRemove() {
        super("entity remove");
        setPermission("snbt.entity");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
                                   List<Object> baseParameters) throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.primaryMap;
        if (!commandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        } else if (!(commandMap.get(player) instanceof Entity)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        }

        Entity entity = (Entity) commandMap.get(player);
        commandMap.removePlayer(player);
        entity.remove();

        return new CommandResponse("&aEntity removed.");
    }

}
