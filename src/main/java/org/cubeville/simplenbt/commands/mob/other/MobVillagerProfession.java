package org.cubeville.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobVillagerProfession extends Command {

	public MobVillagerProfession() {
		super("villager profession");
        setPermission("snbt.mob.other");
		addBaseParameter(new CommandParameterEnum(Profession.class));
	}
	
	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6villager &cor&6 zombie villager&c!");
		} else if (!(commandMap.get(player) instanceof Zombie) && !(commandMap.get(player) instanceof Villager)) {
			throw new CommandExecutionException("&cPlease select a &6villager &cor&6 zombie villager&c!");
		}
		
		if (commandMap.get(player) instanceof Zombie)
			if (!((Zombie) commandMap.get(player)).isVillager())
				throw new CommandExecutionException("&cZombiw must be a &6zombie villager&c!");
			else
				((Zombie) commandMap.get(player)).setVillagerProfession((Profession) baseParameters.get(0));
		
		if (commandMap.get(player) instanceof Villager) {
			((Villager) commandMap.get(player)).setProfession((Profession) baseParameters.get(0));
		}

		return new CommandResponse("&aVillager profession changed to &6" + ((Profession) baseParameters.get(0)).name());
	}
}
