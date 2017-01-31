package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobSheepColor extends Command {

	public MobSheepColor() {
		super("sheep color");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterEnum(DyeColor.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6sheep&c!");
		} else if (!(commandMap.get(player) instanceof Sheep)) {
			throw new CommandExecutionException("&cPlease select a &6sheep&c!");
		}
		
		Sheep sheep = (Sheep) commandMap.get(player);
		sheep.setColor((DyeColor) baseParameters.get(0));

		return new CommandResponse("&aSheep color changed to " + ((DyeColor) baseParameters.get(0)).name());
	}
}
