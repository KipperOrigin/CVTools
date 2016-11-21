package kipperorigin.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.cubeville.commons.Command;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class EntitySelect extends Command {

	public EntitySelect() {
		super("entity select");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		if (!CommandMapManager.getEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getEntityCommandMap().put(player.getName(), null);
		
		if (CommandMapManager.getLivingEntityCommandMap().containsKey(player.getName()))
			CommandMapManager.getLivingEntityCommandMap().remove(player.getName());
		
		player.sendMessage(Colorize.addColor("&aYou may now select a &6entity&a!"));
	}

}

