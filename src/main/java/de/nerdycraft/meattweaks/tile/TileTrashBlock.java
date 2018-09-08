package de.nerdycraft.meattweaks.tile;

import de.nerdycraft.meattweaks.gui.ContainerTrashBlock;
import de.nerdycraft.meattweaks.gui.GuiTrashBlock;
import de.nerdycraft.meattweaks.gui.common.ContainerBase;
import de.nerdycraft.meattweaks.tile.common.IContainerTile;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileTrashBlock extends TileEntity implements IContainerTile {
	
	private static int InvSize = 1;
	private static final int GUIWIDTH = 184;
	private static final int GUIHEIGHT = 184;

    
    private ItemStackHandler itemStackHandler = new ItemStackHandler(InvSize) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
        	TileTrashBlock.this.markDirty();
        }
        
		@Override
		public int getSlots() {
			return InvSize;
		}
        
		@Override
		public ItemStack getStackInSlot(int slot) {
			return ItemStack.EMPTY;
		}
        
		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			return ItemStack.EMPTY;
		}
		
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			return ItemStack.EMPTY;
		}
		
    };
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

	@Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
	public ItemStack getStackInSlot(int slot) {
		return this.itemStackHandler.getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.itemStackHandler.setStackInSlot(slot, stack);		
	}

	@Override
	public int getSlotCount() {
		return this.itemStackHandler.getSlots();
	}

	@Override
	public ContainerBase createContainer(EntityPlayer player) {
		return new ContainerTrashBlock(player, this);
	}

	@Override
	public GuiContainer createGUI(EntityPlayer player) {
		return new GuiTrashBlock(new ContainerTrashBlock(player, this));
	}

	@Override
	public int getGuiXSize() {
		return GUIWIDTH;
	}

	@Override
	public int getGuiYSize() {
		return GUIHEIGHT;
	}
}