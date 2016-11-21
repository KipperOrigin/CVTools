package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobDeselect extends Command {

	public MobDeselect() {
		super("mob deselect");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		Map<String, Entity> entityCommandMap = CommandMapManager.getEntityCommandMap();
		if (commandMap.containsKey(player.getName())) {
			if (commandMap.get(player.getName()) == null) {
				player.sendMessage(Colorize.addColor("&cSelection cancelled!"));
			} else
				if (commandMap.get(player.getName()).getCustomName() != null)
					player.sendMessage(Colorize.addColor("&cMob &6" + commandMap.get(player.getName()).getCustomName() + "&c deselected!"));
				else
					player.sendMessage(Colorize.addColor("&cMob &6" + commandMap.get(player.getName()).getName() + "&c deselected!"));
			
			commandMap.remove(player.getName());
			
			if (entityCommandMap.containsKey(player.getName()))
				entityCommandMap.remove(player.getName());
		} else {
			player.sendMessage(Colorize.addColor("&cCannot cancel invalid block selection!"));
		}
	}

}