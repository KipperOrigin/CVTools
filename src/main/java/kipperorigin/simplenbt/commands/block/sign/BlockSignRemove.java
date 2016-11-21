package kipperorigin.simplenbt.commands.block.sign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterInteger;

import kipperorigin.simplenbt.commands.CommandMapManager;
import kipperorigin.simplenbt.resources.Colorize;

public class BlockSignRemove extends Command {

	public BlockSignRemove() {
		super("block sign remove");
		addBaseParameter(new CommandParameterInteger());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		Map<String, Block> commandMap = CommandMapManager.getBlockCommandMap();
		
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		} else if (commandMap.get(player.getName()) == null || !(commandMap.get(player.getName()) instanceof Sign)) {
 			player.sendMessage(Colorize.addColor("&cPlease select a &6sign&c!"));
			return;
		}
		
		if ((int) baseParameters.get(0) > 4 || (int) baseParameters.get(0) < 0) {
 			player.sendMessage(Colorize.addColor("&cInvalid sign line!"));
			return;
		}
		
		Sign sign = (Sign) commandMap.get(player.getName());
		sign.setLine((int) baseParameters.get(0) - 1, "");
		sign.update();
	}
}

