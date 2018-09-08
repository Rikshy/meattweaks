package de.nerdycraft.meattweaks.tile;

import de.nerdycraft.meattweaks.gui.ContainerCraftBlock;
import de.nerdycraft.meattweaks.gui.GuiCraftBlock;
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

public class TileCraftBlock extends TileEntity  implements IContainerTile {

		private static int InvSize = 9;
		private static final int GUIWIDTH = 176;
		private static final int GUIHEIGHT = 166;
		
	    private ItemStackHandler itemStackHandler = new ItemStackHandler(InvSize) {
	    	@Override
	    	public ItemStack getStackInSlot(int slot) {
	    		ItemStack stack = super.getStackInSlot(slot);
				return stack == null ? ItemStack.EMPTY : stack;	    		
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
			return new ContainerCraftBlock(player, this);
		}

		@Override
		public GuiContainer createGUI(EntityPlayer player) {
			return new GuiCraftBlock(new ContainerCraftBlock(player, this));
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
