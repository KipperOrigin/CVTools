package kipperorigin.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterPotionEffectType;

import kipperorigin.simplenbt.nbt.PotionItem;

public class PotionEffectRemove extends Command {

	public PotionEffectRemove() {
		super("potion remove");
		addBaseParameter(new CommandParameterPotionEffectType());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {

		if (player.getInventory().getItemInMainHand().getType() != Material.POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.LINGERING_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.SPLASH_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.TIPPED_ARROW)
			return;
		
		PotionItem potionItem = new PotionItem(player.getInventory().getItemInMainHand());
		
		potionItem.removeEffect(PotionEffectType.getByName((String) baseParameters.get(0)));
		
		player.getInventory().setItemInMainHand(potionItem.asItemStack());
	}

}
