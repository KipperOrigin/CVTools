package org.cubeville.cvtools.commands.simplenbt.mob;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterFloat;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.cvtools.commands.CommandMapManager;
import org.cubeville.commons.utils.AdvancedSlots;
import org.cubeville.commons.utils.Colorize;

public class MobEquipment extends Command {
	
	public MobEquipment() {
		super("mob equipment");
		addBaseParameter(new CommandParameterEnum(EquipmentSlot.class));
		addParameter("chance", true, new CommandParameterFloat());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		Map<String, LivingEntity> commandMap = CommandMapManager.getLivingEntityCommandMap();
		if (!commandMap.containsKey(player.getName())) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return null;
		} else if (commandMap.get(player.getName()) == null) {
			player.sendMessage(Colorize.addColor("&cPlease select a &6mob&c!"));
			return null;
		}
		
		boolean chance = parameters.containsKey("chance");
		float c = 0;
		EquipmentSlot slot = (EquipmentSlot) (baseParameters.get(0));
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (chance)
			c = (float) parameters.get("chance");
		
		item = AdvancedSlots.setEquipmentByName(commandMap.get(player.getName()), slot, item, chance, c);
		
		if (!chance)
			player.getInventory().setItemInMainHand(item);

                return null;
	}
}
