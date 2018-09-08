package de.nerdycraft.meattweaks.gui.common;

import javax.annotation.Nullable;

import de.nerdycraft.meattweaks.tile.common.IContainerTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class ContainerBase extends Container {

    protected IContainerTile te;
	
	public ContainerBase(IInventory playerInventory, IContainerTile te, int xSize, int ySize) {
        this.te = te;

        // This container references items out of our own inventory (the 9 slots we hold ourselves)
        // as well as the slots from the player inventory so that the user can transfer items between
        // both inventories. The two calls below make sure that slots are defined for both inventories.
        addOwnSlots(playerInventory, this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
        addPlayerSlots(playerInventory, xSize, ySize);
    }
	
	protected void addPlayerSlots(IInventory playerInventory, int xSize, int ySize) {

        int leftCol = (xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++)
        {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++)
            {
                this.addSlotToContainer(
                        new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        // Slots for the hotbar
        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            this.addSlotToContainer(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, ySize - 24));
        }
    }

    protected abstract void addOwnSlots(IInventory playerInventory, IItemHandler itemHandler);

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.canInteractWith(playerIn);
	}

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.te.getSlotCount()) {
                if (!this.mergeItemStack(itemstack1, this.te.getSlotCount(), this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.te.getSlotCount(), false)) {
                return null;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

}
