package org.cubeville.cvtools.commands.simplenbt.firework;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

public class FireworkEffectRemove extends Command {

    public FireworkEffectRemove() {                                                                     
        super("firework remove");
		addBaseParameter(new CommandParameterInteger());
    }

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (item.getType() != Material.FIREWORK) {
			throw new CommandExecutionException("&cMust be holding a &6firework&c!");
		}
		
		FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();
		int i = (int) baseParameters.get(0);
		
		if (i <= fireworkMeta.getEffectsSize() && !(i < 0)) {
			fireworkMeta.removeEffect((int) baseParameters.get(0));
		} else {
			throw new CommandExecutionException("&cLine &6" + baseParameters.get(i) + " &adoes not exist for this firework!");
		}
		
		item.setItemMeta(fireworkMeta);
		
		return new CommandResponse("&aFirework effect removed from line &6" + baseParameters.get(0));
	}

}

