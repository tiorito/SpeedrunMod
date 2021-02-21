package canalcubocraft.mod.items.tools;

import canalcubocraft.mod.Main;
import canalcubocraft.mod.init.ModItems;
import canalcubocraft.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel {

	public ToolAxe(String name, ToolMaterial material) {
		super(material, 12.0F, -3.2F);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);

		ModItems.ITEMS.add(this);

	}

	@Override
	public void registerModels()

	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
