package kipperorigin.simplenbt.commands.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.cubeville.commons.Command;
import org.cubeville.commons.CommandParameterEnum;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobHorseColor extends Command {

	public MobHorseColor() {
		super("mob horse color");
		addBaseParameter(new CommandParameterEnum(Color.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Horse)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return;
		}
		
		Horse horse = (Horse) commandMap.get(player.getName());
		
		if (horse.getVariant() != Variant.HORSE) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6normal horse&c!"));
			return;
		}
		
		horse.setColor((Color) baseParameters.get(0));
	}
}
