package canalcubocraft.mod.items.armor;

import canalcubocraft.mod.Main;
import canalcubocraft.mod.init.ModItems;
import canalcubocraft.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel {

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		// TODO Auto-generated constructor stub

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);

		ModItems.ITEMS.add(this);

	}

	@Override
	public void registerModels()

	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
