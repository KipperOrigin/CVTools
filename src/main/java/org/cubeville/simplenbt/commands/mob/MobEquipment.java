package org.cubeville.simplenbt.commands.mob;

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
import org.cubeville.commons.utils.AdvancedSlots;
import org.cubeville.cvtools.commands.CommandMap;
import org.cubeville.cvtools.commands.CommandMapManager;

public class MobEquipment extends Command {
	
	public MobEquipment() {
		super("mob equipment");
        setPermission("snbt.mob");
		addBaseParameter(new CommandParameterEnum(EquipmentSlot.class));
		addParameter("chance", true, new CommandParameterFloat());
	}

	@Override
	public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
			throws CommandExecutionException {
		CommandMap commandMap = CommandMapManager.primaryMap;
		if (!commandMap.contains(player)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		} else if (!(commandMap.get(player) instanceof LivingEntity)) {
			throw new CommandExecutionException("&cPlease select a &6mob&c!");
		}
		
		boolean chance = parameters.containsKey("chance");
		float c = 0;
		EquipmentSlot slot = (EquipmentSlot) (baseParameters.get(0));
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (chance) {
			c = (float) parameters.get("chance");
			return new CommandResponse("&aDrop chance for &6" + slot.name() + " &afor &6" + ((LivingEntity) commandMap.get(player)).getCustomName() + " &aset to " + c);
		} else {
			item = AdvancedSlots.setEquipmentByName((LivingEntity) commandMap.get(player), slot, item, chance, c);
			player.getInventory().setItemInMainHand(item);
			return new CommandResponse("&aItem for &6" + slot.name() + " &afor &6" + ((LivingEntity) commandMap.get(player)).getCustomName() + " &aset to " + player.getInventory().getItemInMainHand().getType() );
		}
	}
}
