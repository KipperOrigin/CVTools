package kipperorigin.simplenbt.commands.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.cubeville.commons.Command;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobSnowmanDerp extends Command {

	public MobSnowmanDerp() {
		super("mob snowman derp");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6snowman&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Snowman)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6snowman&c!"));
			return;
		}
		
		Snowman snowman = (Snowman) commandMap.get(player.getName());
		
		if (snowman.isDerp())
			snowman.setDerp(false);
		else 
			snowman.setDerp(true);
	}
}