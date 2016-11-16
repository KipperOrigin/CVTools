package kipperorigin.simplenbt.commands.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import kipperorigin.simplenbt.commands.commandparser.Command;
import kipperorigin.simplenbt.commands.commandparser.CommandParameterInteger;
import kipperorigin.simplenbt.nbt.BannerItem;

public class BannerPatternRemove extends Command {

    public BannerPatternRemove() {                                                                     
        super("banner remove");
        addTextParameter(new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> textParameters) {
		BannerItem banner = null;
		
		try {
			banner = new BannerItem(player.getInventory().getItemInMainHand());
		} catch (IllegalArgumentException e) {
			return;
		}
		
		banner.removePattern((int) textParameters.get(0));
		player.getInventory().setItemInMainHand(banner.asItemStack());
	}
}

