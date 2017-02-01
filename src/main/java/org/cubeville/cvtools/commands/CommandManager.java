package org.cubeville.cvtools.commands;

import org.cubeville.commons.commands.CommandParser;
import org.cubeville.cvtools.CVTools;
import org.cubeville.cvtools.commands.other.*;
import org.cubeville.cvtools.commands.stopwatch.*;
import org.cubeville.pvp.commands.*;
import org.cubeville.simplenbt.commands.armor.*;
import org.cubeville.simplenbt.commands.banner.*;
import org.cubeville.simplenbt.commands.block.sign.*;
import org.cubeville.simplenbt.commands.book.*;
import org.cubeville.simplenbt.commands.entity.*;
import org.cubeville.simplenbt.commands.firework.*;
import org.cubeville.simplenbt.commands.item.*;
import org.cubeville.simplenbt.commands.item.attributes.*;
import org.cubeville.simplenbt.commands.item.enchantments.*;
import org.cubeville.simplenbt.commands.item.flags.*;
import org.cubeville.simplenbt.commands.item.lore.*;
import org.cubeville.simplenbt.commands.mob.*;
import org.cubeville.simplenbt.commands.mob.armorstand.*;
import org.cubeville.simplenbt.commands.mob.horse.*;
import org.cubeville.simplenbt.commands.mob.other.*;
import org.cubeville.simplenbt.commands.potion.*;
import org.cubeville.simplenbt.commands.selection.*;
import org.cubeville.simplenbt.commands.skull.*;

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
		snbtCommandParser.addCommand(new EntityInvulnerable());
                snbtCommandParser.addCommand(new EntityMove());
		snbtCommandParser.addCommand(new EntityName());
		snbtCommandParser.addCommand(new EntityPainting());
		snbtCommandParser.addCommand(new EntityRemove());
		snbtCommandParser.addCommand(new EntityRide());
                snbtCommandParser.addCommand(new EntityRotate());
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
                toolsCommandParser.addCommand(new CheckEntities());
		toolsCommandParser.addCommand(new CheckSign());
		toolsCommandParser.addCommand(new DelayedTask(plugin));
                toolsCommandParser.addCommand(new FillRegion());
                toolsCommandParser.addCommand(new SelectRegion());
                toolsCommandParser.addCommand(new FillSelection());
		toolsCommandParser.addCommand(new Info());

		// STOPWATCH
		toolsCommandParser.addCommand(new StopWatchStop());
		toolsCommandParser.addCommand(new StopWatchGet());
		toolsCommandParser.addCommand(new StopWatchRestart());
		toolsCommandParser.addCommand(new StopWatchStart());
	}
	
	public static void registerPvPCommands() {
		pvpCommandParser = new CommandParser();
		
		pvpCommandParser.addCommand(new LoadoutApply());
		pvpCommandParser.addCommand(new LoadoutBlacklistPlayer());
		pvpCommandParser.addCommand(new LoadoutCreate());
		pvpCommandParser.addCommand(new LoadoutClone());
		pvpCommandParser.addCommand(new LoadoutEdit());	
		pvpCommandParser.addCommand(new LoadoutInfo());
		pvpCommandParser.addCommand(new LoadoutList());
		pvpCommandParser.addCommand(new LoadoutRemove());
		pvpCommandParser.addCommand(new LoadoutTagAdd());
		pvpCommandParser.addCommand(new LoadoutTagClear());
		pvpCommandParser.addCommand(new LoadoutTagRemove());
		pvpCommandParser.addCommand(new LoadoutUnblacklistPlayer());
	}
	
	public static void nullifyCommandParsers() {
		snbtCommandParser = null;
		toolsCommandParser = null;
	}
}
