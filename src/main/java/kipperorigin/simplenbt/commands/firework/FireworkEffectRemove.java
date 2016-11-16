package kipperorigin.simplenbt.commands.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;

public class FireworkEffectRemove extends Command {

    public FireworkEffectRemove() {                                                                     
        super("firework remove");
		addTextParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item.getType() != Material.FIREWORK)
			return;
		
		FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();
		fireworkMeta.removeEffect((int) textParameters.get(0));
		item.setItemMeta(fireworkMeta);
		
		player.getInventory().setItemInMainHand(item);
	}

}

