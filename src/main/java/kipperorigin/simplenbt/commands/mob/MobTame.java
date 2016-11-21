package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterString;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class MobTame extends Command {

	public MobTame() {
		super("mob tame");
		addParameter("player", true, new CommandParameterString());
		addFlag("untame");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6tameable mob&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !Tameable.class.isAssignableFrom(commandMap.get(player.getName()).getClass())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6tameable mob&c!"));
			return;
		}
		
		Tameable entity = (Tameable) commandMap.get(player.getName());
		Player playerT = player;

		if (parameters.containsKey("player"))
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer((String) parameters.get("player"))))
				playerT = Bukkit.getPlayer((String) parameters.get("player"));
			else
				return;
		
		if (!flags.contains("untame"))
			entity.setTamed(false);
		else {
			entity.setTamed(true);
			entity.setOwner(playerT);
		}
	}

}
