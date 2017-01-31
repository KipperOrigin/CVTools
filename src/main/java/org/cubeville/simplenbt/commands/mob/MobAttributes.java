package org.cubeville.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterDouble;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.cvtools.nbt.NBTEntityLiving;
import org.cubeville.cvtools.nbt.NBTEntityLiving.AttributeType;

public class MobAttributes extends Command {

	public MobAttributes() {
		super("mob attributes");
        setPermission("snbt.mob");
		addBaseParameter(new CommandParameterEnum(NBTEntityLiving.AttributeType.class));
		addBaseParameter(new CommandParameterDouble());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		} else if (!(commandMap.get(player) instanceof LivingEntity)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		}
		
		NBTEntityLiving entity = new NBTEntityLiving((Entity) commandMap.get(player));
		if (entity.getAttributes().containsKey(((AttributeType) baseParameters.get(0)).toString())) {
			entity.addAttribute((AttributeType) baseParameters.get(0), (double) baseParameters.get(1));
		} else {
			throw new CommandExecutionException("&cAttribute Type not available for this entity!");
		}
		
		return new CommandResponse("&aAttribute &6" + ((AttributeType) baseParameters.get(0)).name() + " &aset to&6 " + baseParameters.get(1) + " &afor&6 " + entity.getName());
	}

}
