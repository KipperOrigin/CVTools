package kipperorigin.simplenbt.commands.player;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class PlayerEquipItem extends Command {

	public PlayerEquipItem() {
		super("player equip");
		addTextParameter(new CommandParameterString());
		addParameter("player", true, new CommandParameterString());
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		String slot = (String) textParameters.get(0);
		Player playerI = player;
		
		if (parameters.containsKey("player"))
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer((String) parameters.get("player"))))
				playerI = Bukkit.getPlayer((String) parameters.get("player"));
			else
				return;
		
		ItemStack inhand = player.getInventory().getItemInMainHand();
		
		if (inhand == null || inhand.getType() == Material.AIR)
			return;
		
		ItemStack equipped = new ItemStack(Material.AIR);
		
		if (slot.equalsIgnoreCase("head")) {
			if (playerI.getInventory().getHelmet() != null)
				equipped = playerI.getInventory().getHelmet();
			playerI.getInventory().setHelmet(inhand);
		} else if (slot.equalsIgnoreCase("chest")) {
			if (playerI.getInventory().getChestplate() != null)
				equipped = playerI.getInventory().getChestplate();
			playerI.getInventory().setChestplate(inhand);
		} else if (slot.equalsIgnoreCase("legs")) {
			if (playerI.getInventory().getLeggings() != null)
				equipped = playerI.getInventory().getLeggings();
			playerI.getInventory().setLeggings(inhand);
		} else if (slot.equalsIgnoreCase("boots")) {
			if (playerI.getInventory().getBoots() != null)
				equipped = playerI.getInventory().getBoots();
			playerI.getInventory().setBoots(inhand);
		}
		
		if (playerI.getInventory().getItemInMainHand() == null || playerI.getInventory().getItemInMainHand().getType() == Material.AIR)
			playerI.getInventory().setItemInMainHand(equipped);
		else
			playerI.getInventory().setItem(playerI.getInventory().firstEmpty(), equipped);
	}

}
