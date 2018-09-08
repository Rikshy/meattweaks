package de.nerdycraft.meattweaks.blocks;


import java.util.Comparator;
import java.util.stream.Stream;

import de.nerdycraft.meattweaks.Constants;
import de.nerdycraft.meattweaks.MeatTweaks;
import de.nerdycraft.meattweaks.blocks.common.BlockBase;
import de.nerdycraft.meattweaks.blocks.common.ITileEntityProviderEx;
import de.nerdycraft.meattweaks.blocks.common.IVariant;
import de.nerdycraft.meattweaks.tile.TileCraftBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockCraftBlock extends BlockBase implements ITileEntityProviderEx {
	public static final IProperty<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);

	public BlockCraftBlock() {
		super("craftblock", Material.WOOD);
		setHardness(1.0f);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCraftBlock();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileCraftBlock.class;
	}
	

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState().withProperty(VARIANT, EnumVariant.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}
	
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileCraftBlock)) {
            return false;
        }
        player.openGui(MeatTweaks.instance, Constants.GUI_ID_CRAFT, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
    
    public String getName(final ItemStack stack) {
		final int metadata = stack.getMetadata();

		return EnumVariant.byMetadata(metadata).getName();
	}

	public enum EnumVariant implements IVariant {
		VARIANT_OAK(0, "oak"),
		VARIANT_SPRUCE(1, "spruce"),
		VARIANT_BIRCH(2, "birch"),
		VARIANT_JUNGLE(3, "jungle"),
		VARIANT_ACACIA(4, "acacia"),
		VARIANT_DARK(5, "dark");

		public static final EnumVariant VALUES[] = values();
		private static final EnumVariant[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumVariant::getMeta)).toArray(EnumVariant[]::new);

		private final int meta;
		private final String name;

		EnumVariant(final int meta, final String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public static EnumVariant byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}
	}
}
