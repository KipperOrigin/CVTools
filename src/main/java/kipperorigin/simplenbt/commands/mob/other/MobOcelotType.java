package kipperorigin.simplenbt.commands.mob.other;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Ocelot.Type;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterEnum;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobOcelotType extends Command {

	public MobOcelotType() {
		super("mob ocelot type");
		addBaseParameter(new CommandParameterEnum(Type.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6ocelot&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Ocelot)) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6ocelot&c!"));
			return;
		}
		
		((Ocelot) commandMap.get(player.getName())).setCatType((Type) baseParameters.get(0));
	}
}
