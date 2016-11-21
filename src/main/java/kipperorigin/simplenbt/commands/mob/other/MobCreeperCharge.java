package kipperorigin.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobCreeperCharge extends Command {

	public MobCreeperCharge() {
		super("mob creeper charge");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6creeper&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Creeper)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6creeper&c!"));
			return;
		}
		
		Creeper creeper = (Creeper) commandMap.get(player.getName());
		
		if (creeper.isPowered())
			creeper.setPowered(false);
		else
			creeper.setPowered(true);
	}
}
