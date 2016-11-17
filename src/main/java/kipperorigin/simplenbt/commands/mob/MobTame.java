package kipperorigin.simplenbt.commands.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.MobCommandMap;

public class MobTame extends Command {

	public MobTame() {
		super("mob tame");
		addParameter("player", true, new CommandParameterString());
		addFlag("untame");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		if (!MobCommandMap.containsKey(player)) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6tameable mob&c!"));
			return;
		} else if (MobCommandMap.getValue(player) == null || !Tameable.class.isAssignableFrom(MobCommandMap.getValue(player).getClass())) {
			player.sendMessage(Colorize.addColor("&cPlease select an &6tameable mob&c!"));
			return;
		}
		
		Tameable entity = (Tameable) MobCommandMap.getValue(player);
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
