package canalcubocraft.mod.init;

import java.util.ArrayList;
import java.util.List;

import canalcubocraft.mod.items.ItemBase;
import canalcubocraft.mod.items.armor.ArmorBase;
import canalcubocraft.mod.items.tools.ToolAxe;
import canalcubocraft.mod.items.tools.ToolHoe;
import canalcubocraft.mod.items.tools.ToolPickaxe;
import canalcubocraft.mod.items.tools.ToolSpade;
import canalcubocraft.mod.items.tools.ToolSword;
import canalcubocraft.mod.util.Reference;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	// materials
	public static final ToolMaterial MATERIAL_RUBY = EnumHelper.addToolMaterial("material_ruby", 3, 600, 5.0F, 4.0F,
			22);
	public static final ToolMaterial MATERIAL_SPEEDRUN = EnumHelper.addToolMaterial("material_speedrun", 3, 1000000,
			20.0F, 36.0F, 22);
	public static final ToolMaterial MATERIAL_RUBY_SWORD = EnumHelper.addToolMaterial("ruby_sword", 3, 1000, 20.0F,
			16.0F, 22);

	public static final ArmorMaterial ARMOR_MATERIAL_RUBY = EnumHelper.addArmorMaterial("armor_material_ruby",
			Reference.MOD_ID + "ruby", 1056, new int[] { 6, 16, 12, 6 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 4.0F);

	public static final Item RUBY = new ItemBase("ruby");

	// Tools

	// Tools Swords
	public static final ItemSword RUBY_SWORD = new ToolSword("ruby_sword", MATERIAL_RUBY_SWORD);
	public static final ItemSword SPEEDRUN_SWORD = new ToolSword("speedrun_sword", MATERIAL_SPEEDRUN);

	public static final ItemSpade RUBY_SHOVEL = new ToolSpade("ruby_shovel", MATERIAL_RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", MATERIAL_RUBY);
	public static final ItemAxe RUBY_AXE = new ToolAxe("ruby_axe", MATERIAL_RUBY);
	public static final ItemHoe RUBY_HOE = new ToolHoe("ruby_hoe", MATERIAL_RUBY);

	// Armor
	public static final Item RUBY_HELMET = new ArmorBase("ruby_helmet", ARMOR_MATERIAL_RUBY, 1,
			EntityEquipmentSlot.HEAD);
	public static final Item RUBY_CHESTPLATE = new ArmorBase("ruby_chestplate", ARMOR_MATERIAL_RUBY, 1,
			EntityEquipmentSlot.CHEST);
	public static final Item RUBY_LEGGINGS = new ArmorBase("ruby_leggings", ARMOR_MATERIAL_RUBY, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item RUBY_BOOTS = new ArmorBase("ruby_boots", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.FEET);

}
