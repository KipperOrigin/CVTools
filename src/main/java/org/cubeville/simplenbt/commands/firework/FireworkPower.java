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
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

public class FireworkPower extends Command {

    public FireworkPower() {                                                                     
        super("firework power");
        setPermission("snbt.firework");
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
		int power = (int) baseParameters.get(0);
		
		if (power < 0 || power > 10) throw new CommandExecutionException("&cThe power of &6" + power + " &cis invalid for fireworks!");
		
		fireworkMeta.setPower(power);
		item.setItemMeta(fireworkMeta);

		return new CommandResponse("&aFirework power set to &6" + baseParameters.get(0));
	}
}

