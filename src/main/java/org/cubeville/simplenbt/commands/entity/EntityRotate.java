package org.cubeville.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.commons.commands.CommandParameterFloat;

public class EntityRotate extends Command {

    public EntityRotate() {
        super("entity rotate");
        setPermission("snbt.entity");
        addFlag("pitch");
        addBaseParameter(new CommandParameterFloat());
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
        float amount = (float) baseParameters.get(0);
        if(flags.contains("pitch")) {
            float newPitch = location.getPitch() + amount;
            if(newPitch > 90) newPitch = 90;
            if(newPitch < -90) newPitch = -90;
            location.setPitch(newPitch);
        }
        else {
            location.setYaw((location.getYaw() + amount) % 360);
        }
        entity.teleport(location);
        return new CommandResponse("&aEntity rotated.");
    }

}
