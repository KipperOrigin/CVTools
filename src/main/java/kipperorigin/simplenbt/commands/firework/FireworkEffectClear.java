package kipperorigin.simplenbt.commands.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import kipperorigin.simplenbt.commands.commandparser.Command;

public class FireworkEffectClear extends Command {

    public FireworkEffectClear() {                                                                     
        super("firework clear");
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item.getType() != Material.FIREWORK)
			return;
		
		FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();
		fireworkMeta.clearEffects();
		item.setItemMeta(fireworkMeta);
		
		player.getInventory().setItemInMainHand(item);
	}

}
