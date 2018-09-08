package de.nerdycraft.meattweaks.blocks.common;

import de.nerdycraft.meattweaks.MeatTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {

	protected BlockBase(String name, Material material) {
		super(material);
		
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
		setCreativeTab(MeatTweaks.tabMeatTweaks);
	}

}
