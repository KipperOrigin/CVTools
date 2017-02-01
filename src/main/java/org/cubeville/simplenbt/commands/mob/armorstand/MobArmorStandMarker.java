package org.cubeville.simplenbt.commands.mob.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobArmorStandMarker extends Command {
	
    public MobArmorStandMarker() {
        super("armorstand marker");
        setPermission("snbt.armorstand");
        addBaseParameter(new CommandParameterBoolean());
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        CommandMap commandMap = CommandMapManager.primaryMap;
        if (!commandMap.contains(player)) {
            throw new CommandExecutionException("&cPlease select an &6armor stand&c!");
        } else if (!(commandMap.get(player) instanceof ArmorStand)) {
            throw new CommandExecutionException("&cPlease select an &6armor stand&c!");
        }
		
        ArmorStand stand = (ArmorStand) commandMap.get(player);
		
        stand.setMarker((boolean) baseParameters.get(0));
        return new CommandResponse("&aArmor Stand marker &aset to &6" + Boolean.toString((boolean) baseParameters.get(0)));
    }
}
