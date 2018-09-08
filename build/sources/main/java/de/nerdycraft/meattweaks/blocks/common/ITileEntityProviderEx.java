package de.nerdycraft.meattweaks.blocks.common;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

public interface ITileEntityProviderEx extends ITileEntityProvider {

	public Class<? extends TileEntity> getTileEntityClass();
}
