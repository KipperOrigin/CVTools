package org.cubeville.cvtools.commands.simplenbt.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.Colorize;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSheepColor extends Command {

	public MobSheepColor() {
		super("mob sheep color");
		addBaseParameter(new CommandParameterEnum(DyeColor.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			return new CommandResponse("&cPlease select a &6sheep&c!");
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Sheep)) {
			return new CommandResponse("&cPlease select a &6sheep&c!");
		}
		
		Sheep sheep = (Sheep) commandMap.get(player.getName());
		sheep.setColor((DyeColor) baseParameters.get(0));

                return null;
	}
}
