package kipperorigin.simplenbt.commands.potion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterString;
import kipperorigin.simplenbt.nbt.PotionItem;

public class PotionSetType extends Command {

	public PotionSetType() {
		super("potion type");
		addTextParameter(new CommandParameterString());
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
		String type = (String) textParameters.get(0);
		
		if (type.equalsIgnoreCase("lingering"))
			potionItem.setLingering();
		else if (type.equalsIgnoreCase("splash"))
			potionItem.setSplash();
		else if (type.equalsIgnoreCase("normal"))
			potionItem.setNormal();
		else if (type.equalsIgnoreCase("arrow"))
			potionItem.setTippedArrow();
		else {
			player.sendMessage("Invalid Potion Type");
			return;
		}
		
		player.getInventory().setItemInMainHand(potionItem.asItemStack());
	}

}