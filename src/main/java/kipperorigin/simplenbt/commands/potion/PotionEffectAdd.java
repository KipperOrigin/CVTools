package kipperorigin.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterPotionEffectType;
import kipperorigin.simplenbt.nbt.PotionItem;
import kipperorigin.simplenbt.nbt.PotionItem.NBTPotionEffect;

public class PotionEffectAdd extends Command {

	public PotionEffectAdd() {
		super("potion add");
		addTextParameter(new CommandParameterPotionEffectType());
		addTextParameter(new CommandParameterInteger());
		addTextParameter(new CommandParameterInteger());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {

		if (player.getInventory().getItemInMainHand().getType() != Material.POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.LINGERING_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.SPLASH_POTION 
				&& player.getInventory().getItemInMainHand().getType() != Material.TIPPED_ARROW)
			return;
		
		PotionItem potionItem = new PotionItem(player.getInventory().getItemInMainHand());
		PotionEffectType type = PotionEffectType.getByName((String) textParameters.get(0));
		NBTPotionEffect potionEffect = new NBTPotionEffect();
		int dur = Integer.parseInt((String) textParameters.get(1)) * 20;
		
		
		if (player.getInventory().getItemInMainHand().getType() == Material.LINGERING_POTION)
			dur *= 4;
		else if (player.getInventory().getItemInMainHand().getType() == Material.TIPPED_ARROW)
			dur *= 8;
		
		potionEffect.creatPotionEffect(type, dur, Integer.parseInt((String) textParameters.get(2)) - 1);
		potionItem.addEffect(potionEffect.getPotionEffect());
		
		player.getInventory().setItemInMainHand(potionItem.asItemStack());
	}

}
