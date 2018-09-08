package de.nerdycraft.meattweaks.gui;

import de.nerdycraft.meattweaks.gui.common.ContainerBase;
import de.nerdycraft.meattweaks.tile.common.IContainerTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.items.IItemHandler;

public class ContainerCraftBlock extends ContainerBase {

	private boolean init = false;
	private EntityPlayer player;
	private InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	private IInventory craftResult = new InventoryCraftResult();

	public ContainerCraftBlock(EntityPlayer player, IContainerTile te) {
		super(player.inventory, te, te.getGuiXSize(), te.getGuiYSize());
		this.player = player;

		for (int i = 0; i < 9; i++) {
			craftMatrix.setInventorySlotContents(i, this.te.getStackInSlot(i));
		}
		init = true;
	}

	@Override
	protected void addOwnSlots(IInventory playerInventory, IItemHandler itemHandler) {
		this.addSlotToContainer(new SlotCrafting(player, craftMatrix, craftResult, 9, 124, 35)); // outputslot

		int i = 0;
		for (int h = 0; h < 3; h++) {
			for (int w = 0; w < 3; w++) {
				this.addSlotToContainer(new Slot(craftMatrix, 0 + i++, 30 + (w * 18), 17 + (h * 18)));
			}
		}
	}

	public int getGuiXSize() {
		return this.te.getGuiXSize();
	}

	public int getGuiYSize() {
		return this.te.getGuiYSize();
	}

	public void onCraftMatrixChanged(IInventory inv) {
		craftResult.setInventorySlotContents(0,
				CraftingManager.findMatchingResult(this.craftMatrix, this.te.getWorld()));
		if (init)
			updateTile();
	}

	protected void updateTile() {
		for (int i = 0; i < 9; i++) {
			this.te.setInventorySlotContents(i, craftMatrix.getStackInSlot(i));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		updateTile();
	}
}
