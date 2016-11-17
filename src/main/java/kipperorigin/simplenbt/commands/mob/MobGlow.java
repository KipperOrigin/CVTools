package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobGlow extends Command {

	public MobGlow() {
		super("mob glow");
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
		
		if (entity.isGlowing())
			entity.setGlowing(false);
		else
			entity.setGlowing(true);
	}
}
