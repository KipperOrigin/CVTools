package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobName extends Command {

	public MobName() {
		super("mob name");
		addParameter("name", true, new CommandParameterString());
		addFlag("remove");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return;
		}
		
		LivingEntity entity = MobCommandMap.getValue(player);
		
		if (parameters.containsKey("name") && !flags.contains("remove")) {
			entity.setCustomName(Colorize.addColor((String) parameters.get("name")));
			entity.setCustomNameVisible(true);
		} else if (!parameters.containsKey("name") && flags.contains("remove")) {
			entity.setCustomName("");
			entity.setCustomNameVisible(false);
		}
			
	}
}
