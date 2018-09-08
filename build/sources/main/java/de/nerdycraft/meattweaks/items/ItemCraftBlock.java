package de.nerdycraft.meattweaks.items;

import java.util.Locale;

import de.nerdycraft.meattweaks.blocks.BlockCraftBlock;
import de.nerdycraft.meattweaks.items.common.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemCraftBlock extends ItemBlockBase {

	public ItemCraftBlock(Block block) {
		super(block);
	}
	
    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "tile.meattweaks.craftblock." + BlockCraftBlock.EnumVariant.VALUES[itemstack.getMetadata()].name().toLowerCase(Locale.US);
    }

}
