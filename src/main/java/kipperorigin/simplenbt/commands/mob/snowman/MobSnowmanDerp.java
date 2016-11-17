package kipperorigin.simplenbt.commands.mob.snowman;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobSnowmanDerp extends Command {

	public MobSnowmanDerp() {
		super("mob snowman derp");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select a normal &6horse&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null || !(MobCommandMap.getValue(player) instanceof Snowman)) {
			player.sendMessage(Colorize.addColor("&cPlease select a normal &6horse&c!"));
			return;
		}
		
		Snowman snowman = (Snowman) MobCommandMap.getValue(player);
		
		snowman.setDerp(true);
	}
}