package canalcubocraft.mod.bloks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RubyBlock extends BlockBase {

	public RubyBlock(String name, Material material)

	{
		super(name, material);
		setSoundType(SoundType.LADDER);
		setHardness(50.0F);
		setResistance(1200.0F);
		setHarvestLevel("pickaxe", 3);
		setLightLevel(1);

	}

}
