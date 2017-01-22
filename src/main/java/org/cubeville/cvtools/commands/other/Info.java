package org.cubeville.cvtools.commands.other;

//       add search options for groups of entities

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

public class Info extends Command {

    public Info() {
        super("info");
        Set<String> types = new HashSet<>();
        types.add("world");
        addBaseParameter(new CommandParameterEnumeratedString(types));
    }

    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
        CommandResponse ret = new CommandResponse();
        String type = (String) baseParameters.get(0);
        if(type.equals("world")) {
            World world = player.getLocation().getWorld();
            ret.addMessage("Current world: " + world.getName() + ", UUID = " + world.getUID());
        }
        return ret;
    }
}
