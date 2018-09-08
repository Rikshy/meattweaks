package de.nerdycraft.meattweaks.init;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

import de.nerdycraft.meattweaks.blocks.BlockCraftBlock;
import de.nerdycraft.meattweaks.blocks.BlockTrashBlock;
import de.nerdycraft.meattweaks.blocks.common.ITileEntityProviderEx;
import de.nerdycraft.meattweaks.blocks.common.IVariant;
import de.nerdycraft.meattweaks.items.ItemCraftBlock;
import de.nerdycraft.meattweaks.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

	private ArrayList<Block> blocks = new ArrayList<Block>();

	public ModBlocks() {
		blocks.add(new BlockTrashBlock());
		blocks.add(new BlockCraftBlock());
	}

	public void registerBlocks(IForgeRegistry<Block> reg) {
		for (Block b : blocks) {
			reg.register(b);

			if (b instanceof ITileEntityProviderEx) {
				GameRegistry.registerTileEntity(((ITileEntityProviderEx) b).getTileEntityClass(),
						b.getRegistryName().toString());
			}
		}

	}

	public void registerBlockItems(IForgeRegistry<Item> reg) {
		for (Block b : blocks) {
			if (b instanceof BlockCraftBlock) {
				reg.register(new ItemCraftBlock(b));
			} else {
				reg.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModels() {
		for (Block b : blocks) {
			if (b instanceof BlockCraftBlock) {
				registerVariantBlockItemModels(b.getDefaultState(), BlockCraftBlock.VARIANT);
			} else {
				registerBlockModel(b, 0, "inventory");
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private void registerBlockModel(Block block, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
				new ModelResourceLocation(block.getRegistryName(), id));
	}

	@SideOnly(Side.CLIENT)
	private <T extends IVariant & Comparable<T>> void registerVariantBlockItemModels(final IBlockState baseState,
			final IProperty<T> property) {
		registerVariantBlockItemModels(baseState, property, IVariant::getMeta);
	}

	@SideOnly(Side.CLIENT)
	private <T extends Comparable<T>> void registerVariantBlockItemModels(final IBlockState baseState,
			final IProperty<T> property, final ToIntFunction<T> getMeta) {
		property.getAllowedValues()
				.forEach(value -> registerBlockItemModelForMeta(baseState.withProperty(property, value),
						getMeta.applyAsInt(value)));
	}

	@SideOnly(Side.CLIENT)
	private void registerBlockItemModelForMeta(final IBlockState state, final int metadata) {
		final Item item = Item.getItemFromBlock(state.getBlock());

		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(),
				ClientProxy.propertyStringMapper.getPropertyString(state.getProperties())));
	}
}
