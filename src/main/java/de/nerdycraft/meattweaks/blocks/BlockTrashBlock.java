package de.nerdycraft.meattweaks.blocks;

import de.nerdycraft.meattweaks.Constants;
import de.nerdycraft.meattweaks.MeatTweaks;
import de.nerdycraft.meattweaks.blocks.common.BlockBase;
import de.nerdycraft.meattweaks.blocks.common.ITileEntityProviderEx;
import de.nerdycraft.meattweaks.tile.TileTrashBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrashBlock extends BlockBase implements ITileEntityProviderEx {

	public BlockTrashBlock() {
		super("trashblock", Material.ROCK);
		this.setHardness(3.5F);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTrashBlock();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileTrashBlock.class;
	}
	
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileTrashBlock)) {
            return false;
        }
        player.openGui(MeatTweaks.instance, Constants.GUI_ID_TRASH, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
    
    @Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
}
