package kipperorigin.simplenbt.commands.banner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;

import kipperorigin.simplenbt.nbt.BannerItem;

public class BannerPatternAdd extends Command {

    public BannerPatternAdd() {                                                                     
        super("banner add");  
        addBaseParameter(new CommandParameterEnum(PatternType.class));
        addBaseParameter(new CommandParameterEnum(DyeColor.class));
        addParameter("set", true, new CommandParameterInteger());
        addParameter("insert", true, new CommandParameterInteger());
    }

	@Override
	public void execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) {
		BannerItem banner = null;
		
		try {
			banner = new BannerItem(player.getInventory().getItemInMainHand());
		} catch (IllegalArgumentException e) {
			return;
		}
		
		if (parameters.containsKey("set") && !parameters.containsKey("insert"))
			banner.setPattern((int) parameters.get("set") - 1, (DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
		
		else if (parameters.containsKey("insert") && !parameters.containsKey("set"))
			banner.insertPattern((int) parameters.get("insert") - 1, (DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
		
		else if (!parameters.containsKey("set") && !parameters.containsKey("insert"))
			banner.addPattern((DyeColor) baseParameters.get(1), (PatternType) baseParameters.get(0));
		
		else 
			//addParameterError("Cannot insert and set at the same time!")
			return;
		
		player.getInventory().setItemInMainHand(banner.asItemStack());
	}
}
