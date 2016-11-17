package kipperorigin.simplenbt.commands.mob.horse;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterEnum;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobHorseVariant extends Command {

	public MobHorseVariant() {
		super("horse variant");
		addTextParameter(new CommandParameterEnum(Variant.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6horse&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null || !(MobCommandMap.getValue(player) instanceof Horse)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6horse&c!"));
			return;
		} 
		
		Horse horse = (Horse) MobCommandMap.getValue(player);
		
		horse.setVariant((Variant) textParameters.get(0));
	}

}
