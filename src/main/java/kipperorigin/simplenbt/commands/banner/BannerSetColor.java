package kipperorigin.simplenbt.commands.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.cubeville.commons.Command;
import org.cubeville.commons.CommandParameterEnum;

import kipperorigin.simplenbt.nbt.BannerItem;

public class BannerSetColor extends Command {

    public BannerSetColor() {                                                                     
        super("banner color");
        addBaseParameter(new CommandParameterEnum(DyeColor.class));
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		BannerItem banner = null;
		
		try {
			banner = new BannerItem(player.getInventory().getItemInMainHand());
		} catch (IllegalArgumentException e) {
			return;
		}
		
		banner.setBaseColor((DyeColor) baseParameters.get(0));
		player.getInventory().setItemInMainHand(banner.asItemStack());
	}
}

