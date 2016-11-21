package kipperorigin.simplenbt.commands.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class EntityGlow extends Command {

	public EntityGlow() {
		super("entity glow");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, Entity> commandMap = CommandMapManager.getEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6mob&c!"));
			return;
		}
		
		Entity entity = commandMap.get(player.getName());
		
		if (entity.isGlowing())
			entity.setGlowing(false);
		else
			entity.setGlowing(true);
	}
}
