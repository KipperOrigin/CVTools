package org.cubeville.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.EntityUtils;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityInfo extends Command {

    public EntityInfo() {
        super("entity info");
        setPermission("snbt.entity");
        addFlag("detail");
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.primaryMap;
        if (!commandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        } else if (!(commandMap.get(player) instanceof Entity)) {
            throw new CommandExecutionException("&cPlease select an &6entity&c!");
        }
		
        CommandResponse cr = new CommandResponse("&6--------------------------");
        boolean detail = (flags.contains("detail"));

        for(String string: EntityUtils.getInfo((Entity) commandMap.get(player), detail)) {
            cr.addMessage(string);
        }
		
        cr.addMessage("&6--------------------------");
        return cr;
    }
}
