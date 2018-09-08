package de.nerdycraft.meattweaks.tile.common;

import de.nerdycraft.meattweaks.gui.common.ContainerBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public interface IContainerTile {
	public void readFromNBT(NBTTagCompound compound);

	public NBTTagCompound writeToNBT(NBTTagCompound compound);

	public boolean canInteractWith(EntityPlayer playerIn);

	public boolean hasCapability(Capability<?> capability, EnumFacing facing);

	public <T> T getCapability(Capability<T> capability, EnumFacing facing);
	
	public ItemStack getStackInSlot(int slot);
	
	public void setInventorySlotContents(int slot, ItemStack stack);
	
	public int getSlotCount();
	
	public ContainerBase createContainer(EntityPlayer player);
	
	public GuiContainer createGUI(EntityPlayer player);
	
	public int getGuiXSize();
	
	public int getGuiYSize();

	public World getWorld();
}
