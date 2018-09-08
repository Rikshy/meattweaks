package de.nerdycraft.meattweaks.gui;


import de.nerdycraft.meattweaks.gui.common.ContainerBase;
import de.nerdycraft.meattweaks.tile.common.IContainerTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTrashBlock extends ContainerBase {

	public ContainerTrashBlock(EntityPlayer player, IContainerTile te) {
		super(player.inventory, te, te.getGuiXSize(), te.getGuiYSize());
	}

	@Override
	protected void addOwnSlots(IInventory playerInv, IItemHandler itemHandler) {
		this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 12 + 4 * 18, 8 + 2 * 18));

	}

	public int getGuiXSize() {
		return this.te.getGuiXSize();
	}
	public int getGuiYSize() {
		return this.te.getGuiYSize();
	}

}
