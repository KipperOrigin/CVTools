package org.cubeville.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterVector;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityMove extends Command {

    public EntityMove() {
        super("entity move");
        setPermission("snbt.entity");
        addBaseParameter(new CommandParameterVector());
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.primaryMap;
        if (!commandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        } else if (!(commandMap.get(player) instanceof Entity)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        }
		
        Entity entity = (Entity) commandMap.get(player);
        Location location = entity.getLocation();
        location = location.add((Vector) baseParameters.get(0));
        entity.teleport(location);
        return new CommandResponse("&aEntity moved.");
    }

}
