package kipperorigin.simplenbt.commands;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import kipperorigin.simplenbt.resources.Attributes;
import kipperorigin.simplenbt.resources.Attributes.Attribute;
import kipperorigin.simplenbt.resources.Attributes.AttributeType;
import kipperorigin.simplenbt.resources.BookItem;
import kipperorigin.simplenbt.resources.Colorize;
import kipperorigin.simplenbt.resources.Glow;
import kipperorigin.simplenbt.resources.NBTItem;
import kipperorigin.simplenbt.resources.PotionItem;
import kipperorigin.simplenbt.resources.PotionItem.NBTPotionEffect;

public class NBTCommandExecutor implements CommandExecutor {

	private Colorize colorize = new Colorize();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		
		if (sender instanceof Player)
			player = (Player) sender;
		else return true;
		
		ItemStack item = null;
		
		if (!(player.getInventory().getItemInMainHand() == null) && !(player.getInventory().getItemInMainHand().getType() == Material.AIR))
			item = player.getInventory().getItemInMainHand();
		
		ItemMeta itemMeta = null;
		
		if (item != null)
			if (item.hasItemMeta())
				itemMeta = item.getItemMeta();

		
		if(cmd.getName().equalsIgnoreCase("snbt")) {
			if (item == null) {
				player.sendMessage(colorize.addColor("&6Must be holding an item!"));
				return true;
			}
			
			NBTItem nbtItem = new NBTItem(item);

			if (args.length == 0) {
				player.sendMessage(colorize.addColor("&6/snbt &c<&abook&c||&acolor&c||&adescription&c||&adurability&c||&aenchantment&c||&ahead&c||&ahide&c||&aname&c||&apotion&c||&atype&c>"));
			
			} else if (args[0].equalsIgnoreCase("arrow")) {
				
				/*
				 * ATTRIBUTES
				 */
				
			} else if (args[0].equalsIgnoreCase("attributes")) {
				double d = 0;
				AttributeType attributeType = null;
				String slot = "mainhand";
				String[] slots = {"mainhand","offhand","head","chest","legs","feet"};
				int x = 0;
				
				if (args.length < 2 || args.length > 4) {
					player.sendMessage(colorize.addColor("&c/snbt &6attributes &c<&aarmor&c||&atoughness&c||&adamage&c||&aattspeed&c||&akbresist&c||&ahealth&c||&amovespeed&c>"));
					return true;
				}
				
				Attributes attributes = new Attributes(player.getInventory().getItemInMainHand());
				
				if (args[1].equalsIgnoreCase("armor")) {
					attributeType = AttributeType.GENERIC_ARMOR;
				} else if (args[1].equalsIgnoreCase("toughness")) {
					attributeType = AttributeType.GENERIC_ARMOR_TOUGHNESS;
				} else if (args[1].equalsIgnoreCase("damage")) {
					attributeType = AttributeType.GENERIC_ATTACK_DAMAGE;
					d = -1;
				} else if (args[1].equalsIgnoreCase("attspeed")) {
					attributeType = AttributeType.GENERIC_ATTACK_SPEED;
					d = -4;
				} else if (args[1].equalsIgnoreCase("kbresist")) {
					attributeType = AttributeType.GENERIC_KNOCKBACK_RESISTANCE;
				} else if (args[1].equalsIgnoreCase("health")) {
					attributeType = AttributeType.GENERIC_MAX_HEALTH;
				} else if (args[1].equalsIgnoreCase("movespeed")) {
					attributeType = AttributeType.GENERIC_MOVEMENT_SPEED;
				} else {
					player.sendMessage(colorize.addColor("&c/snbt &6attributes &c<&aarmor&c||&atoughness&c||&adamage&c||&aattspeed&c||&akbresist&c||&ahealth&c||&amovespeed&c>"));
					return true;
				}
				
				if (args.length >= 3)
					try {
						d = d + Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						player.sendMessage(colorize.addColor("&c/snbt attributes &6" + args[1] + " &c<&avalue&c>"));
						player.sendMessage(colorize.addColor("&6Value must be a &anumber&c!"));
						return true;
					}
				else {
					player.sendMessage(colorize.addColor("&c/snbt attributes &6" + args[1] + " &c<&avalue&c>"));
					return true;
				}
					
				if (args.length == 4)
					while (x < slots.length + 1) {
						if (x > slots.length && slot == null) {
							player.sendMessage(colorize.addColor("&c/snbt attributes &6" + args[1] + args[2] + " &c<&amainhand&c||&aoffhand&c||&ahead&c||&achest&c||&alegs&c||&afeet&c>"));
							break;
						} 
						if (args[3].equalsIgnoreCase(slots[x])) {
							slot = slots[x];
							break;
						} else {
							x++;
							continue;
						}
					}

				attributes.add(Attribute.newBuilder().name(args[1]).type(attributeType).amount(d).slot(slot).build());
				player.getInventory().setItemInMainHand(attributes.getStack());
				
				/*
				 * BOOK
				 */
				
			} else if (args[0].equalsIgnoreCase("book")) {
				
				BookItem book;
				
				if (item.getType() == Material.WRITTEN_BOOK)
					book = new BookItem(item);
				else {
					player.sendMessage(colorize.addColor("&cPlease be holding a &awritten book&c!"));
					return true;
				}
				
				if (args.length >= 2) {
					if (args[1].equalsIgnoreCase("colorize")) {
						book.colorizeBook();
						player.getInventory().setItemInMainHand(book.asItemStack());
						return true;
					} else if (args[1].equalsIgnoreCase("unsign")) {
						book.unsign();
						player.getInventory().setItemInMainHand(book.asItemStack());
						return true;
					}
				
					String string;
					
					if (args.length >= 3) {
						string = args[2];
						if (args.length > 3)
							for(int x = 3; x < args.length; x++) {
								string += " ";
								string += args[x];
							}
					} else {
						return true;
					}
					
					if (args[1].equalsIgnoreCase("name") || args[1].equalsIgnoreCase("title")) {
						book.setTitle(colorize.addColor(string));
					} else if (args[1].equalsIgnoreCase("author")) {
						book.setAuthor(colorize.addColor(string));
					}
					
					player.getInventory().setItemInMainHand(book.asItemStack());
				} else {
					player.sendMessage(colorize.addColor("&C/snbt &6book &c<&acolorize&c|&aname&c|&aauthor&c>"));
				}
				
				/* 
				 * COLOR
				 */
				
			} else if (args[0].equalsIgnoreCase("color")) {
				
				LeatherArmorMeta meta = null;
				
				if ((item.getType() == Material.LEATHER_BOOTS) 
						|| (item.getType() == Material.LEATHER_CHESTPLATE) 
						|| (item.getType() == Material.LEATHER_HELMET) 
						|| (item.getType() == Material.LEATHER_LEGGINGS))
					meta = (LeatherArmorMeta) item.getItemMeta();
				
				if (meta == null) {
					player.sendMessage(colorize.addColor("&cPlease be holding &aLeathor armor&c!"));
					return true;
				}
				else {
					if (args.length == 2) {
						if (colorize.getColorFromString(args[1]) == null) {
							player.sendMessage(colorize.addColor("&C/snbt &6color &c<&acolor&c>"));
							player.sendMessage(colorize.addColor("&CA list of &6colors&c can be found at &ahttp://...."));
							return true;
						} else 
							meta.setColor(colorize.getColorFromString(args[1]));
					} else if (args.length == 4) {
						int i[] = {0,0,0};
						try {
							for (int x = 0; x < args.length - 1; x++) {
								i[x] = Integer.parseInt(args[x+1]);
								if (i[x] > 255)
									i[x] = 255;
								if (i[x] < 0)
									i[x] = 0;
							}
						} catch (NumberFormatException e) {
							player.sendMessage(colorize.addColor("&C/snbt &6color &c<&ared&c> <&agreen&c> <&ablue&c>"));
							player.sendMessage(colorize.addColor("&6Colors&c must be numbers!"));
							return true;
						}
						meta.setColor(Color.fromRGB((i[0]), (i[1]), (i[2])));
					} else {
						player.sendMessage(colorize.addColor("&C/snbt &6color &c<&acolor&c>"));
						player.sendMessage(colorize.addColor("&C/snbt &6color &c<&ared&c> <&agreen&c> <&ablue&c>"));
					}
				}
				
				item.setItemMeta(meta);
				player.getInventory().setItemInMainHand(item);
					
				/*
				 * LORE/DESC
				 */
				
			} else if (args[0].equalsIgnoreCase("desc") || args[0].equalsIgnoreCase("description") || args[0].equalsIgnoreCase("lore")) {
				if (args.length < 2) {
					player.sendMessage(colorize.addColor("&c/snbt &6Lore &c<&aadd&c||&aremove&c||&ainsert&c||&aclear&c>"));
					return true;
				}
				
				String string;
				
				if (args[1].equalsIgnoreCase("add")) {
					if (args.length < 3) {
						player.sendMessage(colorize.addColor("&c/snbt Lore &6add &c<&adescription&c>"));
						return true;
					}
					string = args[2];
					for (int x = 3; x < args.length; x++) {
						string += " ";
						string += args[x];
					}
					nbtItem.addLore(colorize.addColor(string));
				} else if (args[1].equalsIgnoreCase("remove")) {
					try {
						nbtItem.removeLore(Integer.parseInt(args[2]) - 1);
					} catch (NumberFormatException e) {
						player.sendMessage(colorize.addColor("&c/snbt Lore &6remove &c<&aline&c>"));
						return true;
					}
				} else if (args[1].equalsIgnoreCase("insert")) {
					if (args.length < 4) {
						player.sendMessage(colorize.addColor("&c/snbt Lore &6insert &c<&aline&c> &c<&adescription&c>"));
						return true;
					}
					
					string = args[3];
					for (int x = 4; x < args.length; x++) {
						string += " ";
						string += args[x];
					}
					
					try {
						nbtItem.replaceLore(Integer.parseInt(args[2]) - 1, colorize.addColor(string));
					} catch (NumberFormatException e) {
						player.sendMessage(colorize.addColor("&c/snbt Lore &6remove &c<&aline&c>"));
						return true;
					}
				} else if (args[1].equalsIgnoreCase("clear"))
					nbtItem.clearLore();
				else {
					player.sendMessage(colorize.addColor("&c/snbt &6Lore &c<&aadd&c||&aremove&c||&ainsert&c||&aclear&c>"));
					return true;
				}
					
				player.getInventory().setItemInMainHand(nbtItem.asItemStack());
				
				/*
				 * DURABILITY
				 */
				
			} else if (args[0].equalsIgnoreCase("durability")) {
				if (args.length != 2) {
					player.sendMessage(colorize.addColor("&c/snbt &6durability &c<&aMAX&c||&aDurability&c||&aUnbreakable&c||&aget&c>"));
					return true;
				} else if (args[1].equalsIgnoreCase("max")) {
					nbtItem.setDurability((short) 0);
				} else if (args[1].equalsIgnoreCase("unbreakable")) {
					nbtItem.setUnbreakable();
				} else if (args[1].equalsIgnoreCase("get")) {
					player.sendMessage(String.valueOf(nbtItem.getMaxDurability()));
				} else {
					try {
						nbtItem.setDurability((short) (nbtItem.getMaxDurability() - Short.parseShort(args[1])));
					} catch (NumberFormatException e) {
						player.sendMessage(colorize.addColor("&c/snbt &6durability &c<&aMAX&c||&aDurability&c||&aUnbreakable&c||&aget&c>"));
						player.sendMessage(colorize.addColor("&CDurability should be a number!"));
						return true;
					}
				}
				
				player.getInventory().setItemInMainHand(nbtItem.asItemStack());
				
				/*
				 * ENCHANTMENT
				 */
				
			} else if (args[0].equalsIgnoreCase("enchantment") || args[0].equalsIgnoreCase("enchant") || args[0].equalsIgnoreCase("ench")) {
				
				if (args.length < 2 || args.length > 4)
					player.sendMessage(colorize.addColor("&C/snbt &6enchantment &c<&aAdd&c||&aRemove&c||&aGlow&c>"));	
				else if (args[1].equalsIgnoreCase("add")) {
					
					int i = 1;
					
					Enchantment ench = null;
					
					try {
						ench = Enchantment.getByName(args[2].toUpperCase());
					} catch (IllegalArgumentException e) {
						player.sendMessage(colorize.addColor("&C/snbt enchantment &6add &c<&aenchantment&c> <&aleve&c>"));
						player.sendMessage(colorize.addColor("&CA list of &6enchantments&c can be found at &ahttp://...."));		
						return true;
					}
					if (args.length == 4)
						try {
							i = Integer.parseInt(args[3]);
						} catch (NumberFormatException e) {
							player.sendMessage(colorize.addColor("&C/snbt enchantment &6add &c<&aenchantment&c> <&aleve&c>"));
							player.sendMessage(colorize.addColor("&6Level&c must be a &anumber&c!"));
							return true;
						}
				
					if (ench == null) {
						player.sendMessage(colorize.addColor("&C/snbt enchantment &6add &c<&aenchantment&c> <&aleve&c>"));
						player.sendMessage(colorize.addColor("&CA list of &6enchantments&c can be found at &ahttp://...."));
						return true;
					}
					
					item.addUnsafeEnchantment(ench, i);

				} else if (args[1].equalsIgnoreCase("remove")) {
					if (args.length < 3)
						return true;
					
					Enchantment ench = null;
					
					try {
						ench = Enchantment.getByName(args[2].toUpperCase());
					} catch (IllegalArgumentException e) {
						player.sendMessage(colorize.addColor("&C/snbt enchantment &6remove &c<&aenchantment&c>"));
						player.sendMessage(colorize.addColor("&CA list of &6enchantments&c can be found at &ahttp://...."));		
						return true;
					}
					
					if (ench == null)
						return true;
					

					if (item.getEnchantments().containsKey(ench)) {
						item.removeEnchantment(ench);
					}
				} else if (args[1].equalsIgnoreCase("glow")) {
					if (args.length > 2)
						return true;
					
					item = Glow.addGlow(item);
					player.sendMessage("ItemGlow");
				} else {
					player.sendMessage(colorize.addColor("&C/snbt &6enchantment &c<&aAdd&c||&aRemove&c||&aGlow&c>"));
				}
				
				player.getInventory().setItemInMainHand(item);
				
				/*
				 * FIREWORK
				 */
				
			} else if (args[0].equalsIgnoreCase("firework")) {	
				
				/*
				 * HEAD
				 */
				
			} else if (args[0].equalsIgnoreCase("head") || args[0].equalsIgnoreCase("skull")) {
				SkullMeta skullMeta = null;
				
				if (item.getType() == Material.SKULL_ITEM)
					skullMeta = (SkullMeta) itemMeta;
				else
					return true;
				
				MaterialData materialData = item.getData();
				materialData.setData((byte) 3);
				item.setData(materialData);
				skullMeta.setOwner(args[2]);
				item.setItemMeta(skullMeta);
				
				player.getInventory().setItemInMainHand(item);

				/*
				 * HIDE
				 */
				
			} else if (args[0].equalsIgnoreCase("hide")) {
				ItemFlag itemFlag = null;
				boolean addremove;
				
				if (args.length != 3) {
					player.sendMessage(colorize.addColor("&c/snbt &6hide &c<&aadd&c||&aremove&c>"));
					return true;
				} else if (args[1].equalsIgnoreCase("add"))
					addremove = true;
				else if (args[1].equalsIgnoreCase("remove"))
						addremove = false;
				else {
					player.sendMessage(colorize.addColor("&c/snbt &6hide &c<&aadd&c||&aremove&c>"));
					return true;
				}
				
				if (args[2].equalsIgnoreCase("all")) {
					if (addremove)
						itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
					else
						itemMeta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
				} else if (args[2].equalsIgnoreCase("attributes"))
					itemFlag = ItemFlag.HIDE_ATTRIBUTES;
				else if (args[2].equalsIgnoreCase("destroys"))
					itemFlag = ItemFlag.HIDE_DESTROYS;
				else if (args[2].equalsIgnoreCase("enchants"))
					itemFlag = ItemFlag.HIDE_ENCHANTS;
				else if (args[2].equalsIgnoreCase("placedon"))
					itemFlag = ItemFlag.HIDE_PLACED_ON;
				else if (args[2].equalsIgnoreCase("effects"))
					itemFlag = ItemFlag.HIDE_POTION_EFFECTS;
				else if (args[2].equalsIgnoreCase("unbreakable"))
					itemFlag = ItemFlag.HIDE_UNBREAKABLE;
				else {
					player.sendMessage(colorize.addColor("&c/snbt hide &6" + args[1] + "&c<&abook&c||&acolor&c||&adescription&c||&adurability&c||&aenchantment&c||&ahead&c||&ahide&c||&aname&c||&apotion&c||&atype&c>"));
					return true;
				}
				
				if (itemFlag != null)
					if (addremove)
						itemMeta.addItemFlags(itemFlag);
					else
						itemMeta.removeItemFlags(itemFlag);
						
				
				item.setItemMeta(itemMeta);
				player.getInventory().setItemInMainHand(item);
				
				/*
				 * NAME
				 */
				
			} else if (args[0].equalsIgnoreCase("name")) {
				if (args.length < 2) {
					player.sendMessage(colorize.addColor("&c/snbt &6name &c<&aname&c>"));
				} else {
					String string = args[1];
					for (int x = 2; x < args.length; x++) {
						string += " ";
						string += args[x];
					}
					nbtItem.setName(colorize.addColor(string));
					player.getInventory().setItemInMainHand(nbtItem.asItemStack());
				}
				
				/*
				 * POTION
				 */
				
			} else if (args[0].equalsIgnoreCase("potion")) {
				if (args.length < 2) {
					player.sendMessage(colorize.addColor("&c/snbt &6potion &c<&aadd&c||&aremove&c||&aclear&c>"));
					return true;
				}
					
				PotionItem potionItem = null;
				if (item.getType() == Material.POTION || item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION)
					potionItem = new PotionItem(item);
				else {
					player.sendMessage(colorize.addColor("&cPlease be holding a &aPotion&c!"));
					return true;
				}
				
				if (args[1].equalsIgnoreCase("add")) {
					
					if (args.length != 5) {
						player.sendMessage(colorize.addColor("&c/snbt potion &6add &c<&aPotion Effect Type&c> &c<&aduration&c> <&alevel&c> <&7yes&6||&7no&c> &c<&7color&c>"));
						return true;
					}
					
					NBTPotionEffect potionEffect = new NBTPotionEffect();
					
					PotionEffectType type = PotionEffectType.getByName(args[2]);
					
					if (type == null) {
						player.sendMessage("Potion Effect Type is null?");
						player.sendMessage(colorize.addColor("&c/snbt potion &6add &c<&aPotion Effect Type&c> &c<&aduration&c> <&alevel&c>"));
						return true;
					}
					
					try {
						potionEffect.creatPotionEffect(type, Integer.parseInt(args[3]) * 20, Integer.parseInt(args[4]) - 1);
					} catch (NumberFormatException e) {
						player.sendMessage(colorize.addColor("&c/snbt potion &6add " + args[2] + " &c<&aduration&c> <&alevel&c>"));
					}
					
					potionItem.addEffect(potionEffect.getPotionEffect());
					
				} else if (args[1].equalsIgnoreCase("remove")) {
					
					if (args.length != 2) {
						player.sendMessage(colorize.addColor("&c/snbt potion &6remove &c<&aPotion Effect Type&c>"));
						player.sendMessage(colorize.addColor("&cA list of &6Potion Effect Types &ccan be found at &aHttp://..."));
						return true;
					} else {
						PotionEffectType type = PotionEffectType.getByName(args[2]);
						
						if (type == null) {
							player.sendMessage(colorize.addColor("&c/snbt potion &6remove &c<&aPotion Effect Type&c>"));
							player.sendMessage(colorize.addColor("&cA list of &6Potion Effect Types &ccan be found at &aHttp://..."));
							return true;
						}
						
						potionItem.removeEffect(type);
					}	
					
				} else if (args[1].equalsIgnoreCase("set")) {
					if (args.length != 2) {
						player.sendMessage(colorize.addColor("&c/snbt potion &6remove &c<&aPotion Effect Type&c>"));
						player.sendMessage(colorize.addColor("&cA list of &6Potion Effect Types &ccan be found at &aHttp://..."));
						return true;
					} else {
						if (args[1].equalsIgnoreCase("splash"))
							potionItem.setSplash();
						else if (args[1].equalsIgnoreCase("lingering"))
							potionItem.setLingering();
						else if (args[1].equalsIgnoreCase("normal"))
							potionItem.setNormal();
					}
				} else if (args[1].equalsIgnoreCase("clear")) {
					
					if (args.length != 2) {
						player.sendMessage(colorize.addColor("&c/snbt &6potion &aclear"));
						return true;
					} else {
						potionItem.clearEffects();
					}
				} else {
					player.sendMessage(colorize.addColor("&c/snbt &6potion &c<&aadd&c||&aremove&c||&aclear&c>"));
				}
				
				player.getInventory().setItemInMainHand(potionItem.asItemStack());
				
				/*
				 * TYPE
				 */
				
			} else if (args[0].equalsIgnoreCase("type") || args[0].equalsIgnoreCase("material")) {
				if (args.length != 2) {
					player.sendMessage(colorize.addColor("&c/snbt &6type &c<&amaterial&c>"));
				} else {
					try {
						nbtItem.setType(Material.valueOf(args[1].toUpperCase()));
					} catch (IllegalArgumentException e) {
						player.sendMessage(colorize.addColor("&c/snbt &6type &c<&amaterial&c>"));
						player.sendMessage(colorize.addColor("&CA list of &6materials&c can be found at &ahttp://...."));
					}
					player.getInventory().setItemInMainHand(nbtItem.asItemStack());
				}
			} else {
				player.sendMessage(colorize.addColor("&6/snbt &c<&abook&c||&acolor&c||&adescription&c||&adurability&c||&aenchantment&c||&ahead&c||&ahide&c||&aname&c||&apotion&c||&atype&c>"));
			}
		}
		return true;
	}

}
