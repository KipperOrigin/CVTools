package kipperorigin.simplenbt.commands;

import org.cubeville.commons.CommandParser;

import kipperorigin.simplenbt.commands.armor.*;
import kipperorigin.simplenbt.commands.banner.*;
import kipperorigin.simplenbt.commands.block.*;
import kipperorigin.simplenbt.commands.block.mobspawner.*;
import kipperorigin.simplenbt.commands.block.sign.*;
import kipperorigin.simplenbt.commands.book.*;
import kipperorigin.simplenbt.commands.entity.*;
import kipperorigin.simplenbt.commands.firework.*;
import kipperorigin.simplenbt.commands.item.*;
import kipperorigin.simplenbt.commands.item.attributes.*;
import kipperorigin.simplenbt.commands.item.enchantments.*;
import kipperorigin.simplenbt.commands.item.flags.*;
import kipperorigin.simplenbt.commands.item.lore.*;
import kipperorigin.simplenbt.commands.mob.*;
import kipperorigin.simplenbt.commands.mob.horse.*;
import kipperorigin.simplenbt.commands.other.*;
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
		
		// BLOCK
		commandParser.addCommand(new BlockDeselect());
		commandParser.addCommand(new BlockSelect());
		
		// BLOCK -- mobspawner
		commandParser.addCommand(new MobSpawner());
		commandParser.addCommand(new MobSpawnerDelay());
		commandParser.addCommand(new MobSpawnerEntity());
		
		// BLOCK -- sign
		commandParser.addCommand(new BlockSignClear());
		commandParser.addCommand(new BlockSignEdit());
		commandParser.addCommand(new BlockSignRemove());
		commandParser.addCommand(new BlockSignSet());
		
		// BOOK
		commandParser.addCommand(new BookMain());
		commandParser.addCommand(new BookColorize());    
		commandParser.addCommand(new BookSetAuthor());
		commandParser.addCommand(new BookSetTitle());
		commandParser.addCommand(new BookUnsign());
		
		// ENTITY
		commandParser.addCommand(new EntityDeselect());
		commandParser.addCommand(new EntityGlow());
		commandParser.addCommand(new EntityName());
		commandParser.addCommand(new EntityRide());
		commandParser.addCommand(new EntitySelect());
		commandParser.addCommand(new EntitySilent());
		
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
		commandParser.addCommand(new MobAI());
		commandParser.addCommand(new MobDeselect());
		commandParser.addCommand(new MobSelect());
		commandParser.addCommand(new MobTame());

		// MOB --horse
		commandParser.addCommand(new MobHorseColor());
		commandParser.addCommand(new MobHorseStyle());
		commandParser.addCommand(new MobHorseVariant());
		
		// MOB --other (this includes all specific mobs that only have 1 class)
		commandParser.addCommand(new MobCreeperCharge());
		commandParser.addCommand(new MobOcelotType());
		commandParser.addCommand(new MobRabbitType());
		commandParser.addCommand(new MobSheepColor());
		commandParser.addCommand(new MobSlimeSize());
		commandParser.addCommand(new MobSnowmanDerp());
		commandParser.addCommand(new MobVillagerProfession());

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
