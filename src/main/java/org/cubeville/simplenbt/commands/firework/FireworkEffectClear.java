package org.cubeville.simplenbt.commands.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandResponse;

public class FireworkEffectClear extends Command {

    public FireworkEffectClear() {                                                                     
        super("firework clear");
        setPermission("snbt.firework");
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item.getType() != Material.FIREWORK) {
			throw new CommandExecutionException("&cMust be holding a &6firework&c!");
		}
		
		FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();
		fireworkMeta.clearEffects();
		item.setItemMeta(fireworkMeta);
		
		player.getInventory().setItemInMainHand(item);
		
		return new CommandResponse("&aEffect successfully cleared from &6firework&6!");
	}

}
