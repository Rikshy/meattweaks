package de.nerdycraft.meattweaks.util;

import java.util.Set;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public enum EnumToolType {

	PICKAXE(Sets.newHashSet(new Block[] { Blocks.COBBLESTONE, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.STONE,
			Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE,
			Blocks.GOLD_ORE, Blocks.GOLD_BLOCK, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.ICE, Blocks.NETHERRACK,
			Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.RAIL,
			Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL, Blocks.MOB_SPAWNER })) {

		@Override
		public boolean canHarvestBlock(ToolMaterial toolMaterial, IBlockState state) {
			Block block = state.getBlock();

			if (block == Blocks.OBSIDIAN) {
				return toolMaterial.getHarvestLevel() == 3;
			} else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE) {
				if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK) {
					if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE) {
						if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE) {
							if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE) {
								if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE) {
									Material material = state.getMaterial();
									return material == Material.ROCK
											|| (material == Material.IRON || material == Material.ANVIL);
								} else {
									return toolMaterial.getHarvestLevel() >= 2;
								}
							} else {
								return toolMaterial.getHarvestLevel() >= 1;
							}
						} else {
							return toolMaterial.getHarvestLevel() >= 1;
						}
					} else {
						return toolMaterial.getHarvestLevel() >= 2;
					}
				} else {
					return toolMaterial.getHarvestLevel() >= 2;
				}
			} else {
				return toolMaterial.getHarvestLevel() >= 2;
			}
		}

		@Override
		public float getDestroySpeed(ItemStack stack, IBlockState block) {
			if (block != null) {
				Material mat = block.getMaterial();
				if (mat == Material.IRON || mat == Material.ANVIL || mat == Material.ROCK)
					return this.efficiencyOnProperMaterial;
			}
			return super.getDestroySpeed(stack, block);
		}
	},
	SHOVEL(Sets.newHashSet(new Block[] { Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.SNOW,
			Blocks.SNOW_LAYER, Blocks.CLAY, Blocks.FARMLAND, Blocks.SOUL_SAND, Blocks.MYCELIUM })) {
		@Override
		public boolean canHarvestBlock(ToolMaterial toolMaterial, IBlockState block) {
			return block.getBlock() == Blocks.SNOW ? true : block.getBlock() == Blocks.SNOW_LAYER;
		}
	},

	AXE(Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.CHEST,
			Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.PUMPKIN })) {

		@Override
		public float getDestroySpeed(ItemStack stack, IBlockState block) {
			if (block != null) {
				Material mat = block.getMaterial();
				if (mat == Material.WOOD || mat == Material.PLANTS || mat == Material.VINE || mat == Material.LEAVES)
					return this.efficiencyOnProperMaterial;
			}
			return super.getDestroySpeed(stack, block);
		}
	};

	private Set<Block> toolBlockSet;
	protected float efficiencyOnProperMaterial = 4.0F;

	EnumToolType(Set<Block> toolBlockSet) {
		this.toolBlockSet = toolBlockSet;
	}

	public Set<Block> getToolBlockSet() {
		return this.toolBlockSet;
	}

	public boolean canHarvestBlock(ToolMaterial toolMaterial, IBlockState block) {
		return false;
	}

	public float getDestroySpeed(ItemStack stack, IBlockState block) {
		return this.toolBlockSet.contains(block.getBlock()) ? this.efficiencyOnProperMaterial : 1.0F;
	}
}
