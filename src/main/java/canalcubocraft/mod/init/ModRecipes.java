package canalcubocraft.mod.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		GameRegistry.addSmelting(ModItems.RUBY, new ItemStack(ModBlocks.RUBY_BLOCK, 5), 7000.0f);
		GameRegistry.addSmelting(new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.LEATHER, 1), 2.0f);

	}
}
