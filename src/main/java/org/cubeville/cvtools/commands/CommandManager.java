package org.cubeville.cvtools.commands;

import org.cubeville.commons.commands.CommandParser;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.other.*;
import org.cubeville.cvtools.commands.simplenbt.armor.*;
import org.cubeville.cvtools.commands.simplenbt.banner.*;
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
import org.cubeville.cvtools.commands.simplenbt.mob.armorstand.*;
import org.cubeville.cvtools.commands.simplenbt.mob.horse.*;
import org.cubeville.cvtools.commands.simplenbt.mob.other.*;
import org.cubeville.cvtools.commands.simplenbt.object.*;
import org.cubeville.cvtools.commands.simplenbt.player.*;
import org.cubeville.cvtools.commands.simplenbt.potion.*;
import org.cubeville.cvtools.commands.simplenbt.skull.*;
import org.cubeville.cvtools.commands.stopwatch.*;
import org.cubeville.pvp.commands.*;

public class CommandManager {
	
	public static CommandParser snbtCommandParser;
	public static CommandParser toolsCommandParser;
	public static CommandParser pvpCommandParser;
	
	public static void registerAllCommands(CVTools plugin) {
		registerSNBTCommands();
		registerToolsCommands(plugin);
		registerPvPCommands();
	}
	
	public static void registerSNBTCommands() {
		snbtCommandParser = new CommandParser();  
		
		// ARMOR
		snbtCommandParser.addCommand(new ArmorColor());
		
		// BANNER
		snbtCommandParser.addCommand(new BannerPatternAdd());
		snbtCommandParser.addCommand(new BannerPatternClear());
		snbtCommandParser.addCommand(new BannerPatternRemove());
		snbtCommandParser.addCommand(new BannerColor());
		
		// BLOCK -- sign
		snbtCommandParser.addCommand(new BlockSignClear());
		snbtCommandParser.addCommand(new BlockSignEdit());
		snbtCommandParser.addCommand(new BlockSignRemove());
		snbtCommandParser.addCommand(new BlockSignSet());
		
		// BOOK
		snbtCommandParser.addCommand(new BookAuthor());
		snbtCommandParser.addCommand(new BookColorize());
		snbtCommandParser.addCommand(new BookTitle());
		snbtCommandParser.addCommand(new BookUnsign());
		
		// ENTITY
		snbtCommandParser.addCommand(new EntityGlow());
		snbtCommandParser.addCommand(new EntityInfo());
		snbtCommandParser.addCommand(new EntityName());
		snbtCommandParser.addCommand(new EntityRide());
		snbtCommandParser.addCommand(new EntitySilent());
		
		// FIREWORK
		snbtCommandParser.addCommand(new FireworkEffectAdd());
		snbtCommandParser.addCommand(new FireworkEffectClear());
		snbtCommandParser.addCommand(new FireworkEffectRemove());
		snbtCommandParser.addCommand(new FireworkPower());
		
		// ITEM	
		snbtCommandParser.addCommand(new ItemDurability());
		snbtCommandParser.addCommand(new ItemName());
		snbtCommandParser.addCommand(new ItemPrintName());
		snbtCommandParser.addCommand(new ItemType());
		
		// ITEM --flags
		snbtCommandParser.addCommand(new ItemFlags());
		snbtCommandParser.addCommand(new ItemFlagsAdd());
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
		snbtCommandParser.addCommand(new MobAttributes());
		snbtCommandParser.addCommand(new MobEquipment());
		snbtCommandParser.addCommand(new MobItemPickup());
		snbtCommandParser.addCommand(new MobTame());
		snbtCommandParser.addCommand(new MobUntame());

		// MOB --armor stand
		snbtCommandParser.addCommand(new MobArmorStandMarker());
		snbtCommandParser.addCommand(new MobArmorStandPoses());
		snbtCommandParser.addCommand(new MobArmorStandSmall());
		snbtCommandParser.addCommand(new MobArmorStandVisible());
		
		// MOB --horse
		snbtCommandParser.addCommand(new MobHorseColor());
		snbtCommandParser.addCommand(new MobHorseStyle());
		snbtCommandParser.addCommand(new MobHorseUntame());
		snbtCommandParser.addCommand(new MobHorseVariant());
		
		// MOB --other (this includes all specific mobs that only have 1 class)
		snbtCommandParser.addCommand(new MobCreeperCharge());
		snbtCommandParser.addCommand(new MobOcelotType());
		snbtCommandParser.addCommand(new MobRabbitType());
		snbtCommandParser.addCommand(new MobSheepColor());
		snbtCommandParser.addCommand(new MobSlimeSize());
		snbtCommandParser.addCommand(new MobSnowmanDerp());
		snbtCommandParser.addCommand(new MobVillagerProfession());

		// OBJECT
		snbtCommandParser.addCommand(new ObjectDeselect());
		snbtCommandParser.addCommand(new ObjectSelect());
		snbtCommandParser.addCommand(new ObjectSelectNearest());
		
		// PLAYER
		snbtCommandParser.addCommand(new PlayerEquipItem());
		
		// POTION
		snbtCommandParser.addCommand(new PotionEffectAdd());
		snbtCommandParser.addCommand(new PotionEffectClear());
		snbtCommandParser.addCommand(new PotionEffectRemove());
		snbtCommandParser.addCommand(new PotionType());
		
		// SKULL
		snbtCommandParser.addCommand(new SkullOwner());
		snbtCommandParser.addCommand(new SkullType());
	}
	
	public static void registerToolsCommands(CVTools plugin) {
		toolsCommandParser = new CommandParser();
		
		// OTHER
		toolsCommandParser.addCommand(new ChatColor());
		toolsCommandParser.addCommand(new CheckSign());
		toolsCommandParser.addCommand(new DelayedTask(plugin));
		
		// STOPWATCH
		toolsCommandParser.addCommand(new StopWatchStop());
		toolsCommandParser.addCommand(new StopWatchGet());
		toolsCommandParser.addCommand(new StopWatchRestart());
		toolsCommandParser.addCommand(new StopWatchStart());
	}
	
	public static void registerPvPCommands() {
		pvpCommandParser = new CommandParser();
		
		pvpCommandParser.addCommand(new LoadoutApply());
		pvpCommandParser.addCommand(new LoadoutCreate());
		pvpCommandParser.addCommand(new LoadoutEdit());	
		pvpCommandParser.addCommand(new LoadoutInfo());
		pvpCommandParser.addCommand(new LoadoutList());
		pvpCommandParser.addCommand(new LoadoutRemove());
		pvpCommandParser.addCommand(new LoadoutTagAdd());
		pvpCommandParser.addCommand(new LoadoutTagClear());
		pvpCommandParser.addCommand(new LoadoutTagRemove());
	}
	
	public static void nullifyCommandParsers() {
		snbtCommandParser = null;
		toolsCommandParser = null;
	}
}
