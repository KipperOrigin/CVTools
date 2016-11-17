package kipperorigin.simplenbt.commands;

import java.util.HashMap;

import kipperorigin.simplenbt.commands.armor.*;
import kipperorigin.simplenbt.commands.banner.*;
import kipperorigin.simplenbt.commands.book.*;
import kipperorigin.simplenbt.commands.commandparser.CommandParser;
import kipperorigin.simplenbt.commands.firework.*;
import kipperorigin.simplenbt.commands.item.*;
import kipperorigin.simplenbt.commands.item.attributes.*;
import kipperorigin.simplenbt.commands.item.enchantments.*;
import kipperorigin.simplenbt.commands.item.flags.*;
import kipperorigin.simplenbt.commands.item.lore.*;
import kipperorigin.simplenbt.commands.mob.*;
import kipperorigin.simplenbt.commands.mob.horse.*;
import kipperorigin.simplenbt.commands.mob.snowman.*;
import kipperorigin.simplenbt.commands.player.*;
import kipperorigin.simplenbt.commands.potion.*;
import kipperorigin.simplenbt.commands.skull.*;

public class CommandManager {
	
	public static CommandParser commandParser;
	
	public static void registerCommands() {
		
		commandParser = new CommandParser();  
		
		// ARMOR
		commandParser.addCommand(new ArmorColorByName());
		commandParser.addCommand(new ArmorColorByRGB());
		
		// BANNER
		commandParser.addCommand(new BannerPatternAdd());
		
		// BOOK
		commandParser.addCommand(new BookMain());
		commandParser.addCommand(new BookColorize());    
		commandParser.addCommand(new BookSetAuthor());
		commandParser.addCommand(new BookSetTitle());
		commandParser.addCommand(new BookUnsign());
		
		// FIREWORK
		commandParser.addCommand(new FireworkEffectAdd());
		commandParser.addCommand(new FireworkEffectClear());
		commandParser.addCommand(new FireworkEffectRemove());
		commandParser.addCommand(new FireworkSetPower());
		
		// ITEM	
		commandParser.addCommand(new ItemSetDurability());
		commandParser.addCommand(new ItemSetName());
		commandParser.addCommand(new ItemSetType());
		
		// ITEM --flags
		commandParser.addCommand(new ItemFlags());
		commandParser.addCommand(new ItemFlagsAdd());
		commandParser.addCommand(new ItemFlagsAddAll());
		commandParser.addCommand(new ItemFlagsClear());
		commandParser.addCommand(new ItemFlagsRemove());
		
		// ITEM --attributes
		commandParser.addCommand(new ItemAttributesAdd());
		commandParser.addCommand(new ItemAttributesClear());
		commandParser.addCommand(new ItemAttributesRemove());
		
		// ITEM --enchantments
		commandParser.addCommand(new ItemEnchantmentsAdd());
		commandParser.addCommand(new ItemEnchantmentsClear());
		commandParser.addCommand(new ItemEnchantmentsRemove());

		// ITEM --lore
		commandParser.addCommand(new ItemLoreAdd());
		commandParser.addCommand(new ItemLoreClear());
		commandParser.addCommand(new ItemLoreRemove());
		
		// MOB
		commandParser.addCommand(new MobAge());
		commandParser.addCommand(new MobDeselect());
		commandParser.addCommand(new MobGlow());
		commandParser.addCommand(new MobName());
		commandParser.addCommand(new MobRide());
		commandParser.addCommand(new MobSelect());
		commandParser.addCommand(new MobTame());
		
		// MOB --horse
		commandParser.addCommand(new MobHorseColor());
		commandParser.addCommand(new MobHorseStyle());
		commandParser.addCommand(new MobHorseVariant());
		
		// MOB --snowman
		commandParser.addCommand(new MobSnowmanDerp());
		
		// PLAYER
		commandParser.addCommand(new PlayerEquipItem());
		
		// POTION
		commandParser.addCommand(new PotionEffectAdd());
		commandParser.addCommand(new PotionEffectClear());
		commandParser.addCommand(new PotionEffectRemove());
		commandParser.addCommand(new PotionSetType());
		
		// SKULL
		commandParser.addCommand(new SkullSetOwner());
		commandParser.addCommand(new SkullSetType());
	}
}
