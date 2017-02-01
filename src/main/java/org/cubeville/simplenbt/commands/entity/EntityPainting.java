package org.cubeville.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Art;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class EntityPainting extends Command {

	public EntityPainting() {
		super("entity painting");
        setPermission("snbt.entity");
		addBaseParameter(new CommandParameterEnum(Art.class));
		addBaseParameter(new CommandParameterBoolean());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters,
	        List<Object> baseParameters) throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select an &6hangable entity&c!");
		} else if (!(commandMap.get(player) instanceof Painting)) {
			throw new CommandExecutionException("&cPlease select an &6hangable entity&c!");
		}
		
		Painting painting = (Painting) commandMap.get(player);
		painting.setArt((Art) baseParameters.get(0), (boolean) baseParameters.get(1));
		
		return new CommandResponse("&aPainting changed to &6" + ((Art) baseParameters.get(0)).toString());
	}
}
