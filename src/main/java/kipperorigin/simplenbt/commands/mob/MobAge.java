package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobAge extends Command {

	public MobAge() {
		super("mob age");
		addParameter("set", true, new CommandParameterInteger());
		addFlag("baby");
		addFlag("adult");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6adeable mob&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null || !Ageable.class.isAssignableFrom(MobCommandMap.getValue(player).getClass())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6ageable mob&c!"));
			return;
		}
		
		Ageable entity = (Ageable) MobCommandMap.getValue(player);
		
		if (parameters.containsKey("set") && !flags.contains("baby") && !flags.contains("adult"))
			if ((int) parameters.get("set") >= 0)
				entity.setAge((int) parameters.get("set"));
			else 
				entity.setAge(0);
		else if (!parameters.containsKey("set") && flags.contains("baby") && !flags.contains("adult"))
			entity.setBaby();
		else if (!parameters.containsKey("set") && !flags.contains("baby") && flags.contains("adult"))
			entity.setAdult();
		
		if (flags.contains("lock"))
			if (entity.getAgeLock())
				entity.setAgeLock(false);
			else
				entity.setAgeLock(true);
	}
}
