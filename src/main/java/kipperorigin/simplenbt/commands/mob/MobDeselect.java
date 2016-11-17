package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobDeselect extends Command {

	public MobDeselect() {
		super("mob deselect");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (MobCommandMap.containsKey(player)) {
			if (MobCommandMap.getValue(player) == null) {
				player.sendMessage(Colorize.addColor("&cSelection cancelled!"));
			} else
				player.sendMessage(Colorize.addColor("&cMob deselected!"));
			
			MobCommandMap.removeEventCommand(player);
		} else {
			player.sendMessage(Colorize.addColor("&cCannot cancel invalid selection!"));
		}
	}

}