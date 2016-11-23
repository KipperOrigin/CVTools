package org.cubeville.cvtools.commands;

import org.cubeville.commons.commands.CommandParser;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.other.*;
import org.cubeville.cvtools.commands.simplenbt.armor.*;
import org.cubeville.cvtools.commands.simplenbt.banner.*;
import org.cubeville.cvtools.commands.simplenbt.block.*;
import org.cubeville.cvtools.commands.simplenbt.block.mobspawner.*;
import org.cubeville.cvtools.commands.simplenbt.block.sign.*;
import org.cubeville.cvtools.commands.simplenbt.book.*;
import org.cubeville.cvtools.commands.simplenbt.entity.*;
import org.cubeville.cvtools.commands.simplenbt.firework.*;
import org.cubeville.cvtools.commands.simplenbt.item.*;
import org.cubeville.cvtools.commands.simplenbt.item.attributes.*;
import org.cubeville.cvtools.commands.simplenbt.item.enchantments.*;
import org.cubeville.cvtools.commands.simplenbt.item.flags.*;
import org.cubeville.cvtools.commands.simplenbt.item.lore.*;
import org.cubeville.cvtools.commands.simplenbt.mob.*;
import org.cubeville.cvtools.commands.simplenbt.mob.horse.*;
import org.cubeville.cvtools.commands.simplenbt.mob.other.*;
import org.cubeville.cvtools.commands.simplenbt.player.*;
import org.cubeville.cvtools.commands.simplenbt.potion.*;
import org.cubeville.cvtools.commands.simplenbt.skull.*;
import org.cubeville.cvtools.commands.stopwatch.*;

public class CommandManager {
	
	public static CommandParser snbtCommandParser;
	public static CommandParser toolsCommandParser;
	
	public static void registerAllCommands(CVTools plugin) {
		registerSNBTCommands();
		registerToolsCommands(plugin);
	}
	
	public static void registerSNBTCommands() {
		snbtCommandParser = new CommandParser();  
		
		// ARMOR
		snbtCommandParser.addCommand(new ArmorColorByName());
		snbtCommandParser.addCommand(new ArmorColorByRGB());
		
		// BANNER
		snbtCommandParser.addCommand(new BannerPatternAdd());
		
		// BLOCK
		snbtCommandParser.addCommand(new BlockDeselect());
		snbtCommandParser.addCommand(new BlockSelect());
		
		// BLOCK -- mobspawner
		snbtCommandParser.addCommand(new MobSpawner());
		snbtCommandParser.addCommand(new MobSpawnerDelay());
		snbtCommandParser.addCommand(new MobSpawnerEntity());
		
		// BLOCK -- sign
		snbtCommandParser.addCommand(new BlockSignClear());
		snbtCommandParser.addCommand(new BlockSignEdit());
		snbtCommandParser.addCommand(new BlockSignRemove());
		snbtCommandParser.addCommand(new BlockSignSet());
		
		// BOOK
		snbtCommandParser.addCommand(new BookMain());
		snbtCommandParser.addCommand(new BookColorize());    
		snbtCommandParser.addCommand(new BookSetAuthor());
		snbtCommandParser.addCommand(new BookSetTitle());
		snbtCommandParser.addCommand(new BookUnsign());
		
		// ENTITY
		snbtCommandParser.addCommand(new EntityDeselect());
		snbtCommandParser.addCommand(new EntityGlow());
		snbtCommandParser.addCommand(new EntityName());
		snbtCommandParser.addCommand(new EntityRide());
		snbtCommandParser.addCommand(new EntitySelect());
		snbtCommandParser.addCommand(new EntitySilent());
		
		// FIREWORK
		snbtCommandParser.addCommand(new FireworkEffectAdd());
		snbtCommandParser.addCommand(new FireworkEffectClear());
		snbtCommandParser.addCommand(new FireworkEffectRemove());
		snbtCommandParser.addCommand(new FireworkSetPower());
		
		// ITEM	
		snbtCommandParser.addCommand(new ItemSetDurability());
		snbtCommandParser.addCommand(new ItemSetName());
		snbtCommandParser.addCommand(new ItemSetType());
		
		// ITEM --flags
		snbtCommandParser.addCommand(new ItemFlags());
		snbtCommandParser.addCommand(new ItemFlagsAdd());
		snbtCommandParser.addCommand(new ItemFlagsAddAll());
		snbtCommandParser.addCommand(new ItemFlagsClear());
		snbtCommandParser.addCommand(new ItemFlagsRemove());
		
		// ITEM --attributes
		snbtCommandParser.addCommand(new ItemAttributesAdd());
		snbtCommandParser.addCommand(new ItemAttributesClear());
		snbtCommandParser.addCommand(new ItemAttributesRemove());
		
		// ITEM --enchantments
		snbtCommandParser.addCommand(new ItemEnchantmentsAdd());
		snbtCommandParser.addCommand(new ItemEnchantmentsClear());
		snbtCommandParser.addCommand(new ItemEnchantmentsRemove());

		// ITEM --lore
		snbtCommandParser.addCommand(new ItemLoreAdd());
		snbtCommandParser.addCommand(new ItemLoreClear());
		snbtCommandParser.addCommand(new ItemLoreRemove());
		
		// MOB
		snbtCommandParser.addCommand(new MobAge());
		snbtCommandParser.addCommand(new MobAI());
		snbtCommandParser.addCommand(new MobDeselect());
		snbtCommandParser.addCommand(new MobSelect());
		snbtCommandParser.addCommand(new MobTame());

		// MOB --horse
		snbtCommandParser.addCommand(new MobHorseColor());
		snbtCommandParser.addCommand(new MobHorseStyle());
		snbtCommandParser.addCommand(new MobHorseVariant());
		
		// MOB --other (this includes all specific mobs that only have 1 class)
		snbtCommandParser.addCommand(new MobCreeperCharge());
		snbtCommandParser.addCommand(new MobOcelotType());
		snbtCommandParser.addCommand(new MobRabbitType());
		snbtCommandParser.addCommand(new MobSheepColor());
		snbtCommandParser.addCommand(new MobSlimeSize());
		snbtCommandParser.addCommand(new MobSnowmanDerp());
		snbtCommandParser.addCommand(new MobVillagerProfession());

		// PLAYER
		snbtCommandParser.addCommand(new PlayerEquipItem());
		
		// POTION
		snbtCommandParser.addCommand(new PotionEffectAdd());
		snbtCommandParser.addCommand(new PotionEffectClear());
		snbtCommandParser.addCommand(new PotionEffectRemove());
		snbtCommandParser.addCommand(new PotionSetType());
		
		// SKULL
		snbtCommandParser.addCommand(new SkullSetOwner());
		snbtCommandParser.addCommand(new SkullSetType());
	}
	
	public static void registerToolsCommands(CVTools plugin) {
		toolsCommandParser = new CommandParser();
		
		// DELAYED TASK
		toolsCommandParser.addCommand(new DelayedTask(plugin));
		
		// STOPWATCH
		toolsCommandParser.addCommand(new StopWatchEnd());
		toolsCommandParser.addCommand(new StopWatchGet());
		toolsCommandParser.addCommand(new StopWatchRestart());
		toolsCommandParser.addCommand(new StopWatchStart());
	}
	
	public static void nullifyCommandParsers() {
		snbtCommandParser = null;
		toolsCommandParser = null;
	}
}
