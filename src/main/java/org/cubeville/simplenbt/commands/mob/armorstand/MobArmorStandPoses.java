package org.cubeville.simplenbt.commands.mob.armorstand;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEulerAngle;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.AdvancedSlots;
import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobArmorStandPoses extends Command {
	
    public MobArmorStandPoses() {
        super("armorstand pose");
        setPermission("snbt.armorstand");
        addBaseParameter(new CommandParameterString());
        addBaseParameter(new CommandParameterEulerAngle());
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
	
        boolean set = AdvancedSlots.setAngleByName(stand, (String) baseParameters.get(0), (EulerAngle) baseParameters.get(1));
	
        if (set) {
            return new CommandResponse("&aAngle Set to &6" + (String) baseParameters.get(0) + "&c!");
        } else {
            player.sendMessage(ColorUtils.addColor("&6" + (String) baseParameters.get(0) + " &cis not a valid armor pose part!"));
            throw new CommandExecutionException("&cValid armor pose parts are: &fhead&c,&fbody&c,&fleft_arm&c,&fright_arm&c,&fleft_leg&c,&fright_leg");
        }
    }
}
