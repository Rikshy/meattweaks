package de.nerdycraft.meattweaks.items.common;

import de.nerdycraft.meattweaks.MeatTweaks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock {

	public ItemBlockBase(Block block) {
		super(block);
		
        this.setRegistryName(block.getRegistryName());
		setUnlocalizedName(getRegistryName().getResourceDomain() + "." + block.getRegistryName());
		setCreativeTab(MeatTweaks.tabMeatTweaks);
	}

}
