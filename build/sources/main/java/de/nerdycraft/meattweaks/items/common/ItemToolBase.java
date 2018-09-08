package de.nerdycraft.meattweaks.items.common;

import java.util.Set;

import javax.annotation.Nullable;

import de.nerdycraft.meattweaks.MeatTweaks;
import de.nerdycraft.meattweaks.util.EnumToolType;
import de.nerdycraft.meattweaks.util.Util;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.entity.player.EntityPlayer;

public class ItemToolBase extends ItemTool {

	private final EnumToolType toolType;
	private final ItemStack repairItem;
	private String toolClass;
	private String repairOreDict;

	private static final float[] AXE_DAMAGES = new float[] { 3.0F, 5.0F, 7.0F, 8.0F, 6.0F };
	private static final float[] AXE_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F };

	public ItemToolBase(String name, ToolMaterial mat, String repairItem, EnumToolType toolType) {
		this(name, mat, ItemStack.EMPTY, toolType);

		this.repairOreDict = repairItem;
	}

	public ItemToolBase(String name, ToolMaterial mat, ItemStack repairItem, EnumToolType toolType) {
		super(1.0F, 2.0F, mat, toolType.getToolBlockSet());
		// TODO Auto-generated constructor stub

		this.repairItem = repairItem;
		this.toolType = toolType;

		setRegistryName(name);
		setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
		setCreativeTab(MeatTweaks.tabMeatTweaks);

		if (toolType == EnumToolType.PICKAXE) {
			this.toolClass = "pickaxe";
			this.attackDamage = 1.0F + toolMaterial.getAttackDamage();
			this.attackSpeed = -2.8F;
		} else if (toolType == EnumToolType.AXE) {
			this.toolClass = "axe";
			this.attackDamage = AXE_DAMAGES[toolMaterial.ordinal()] + toolMaterial.getAttackDamage();
			this.attackSpeed = AXE_SPEEDS[toolMaterial.ordinal()];
		} else if (toolType == EnumToolType.SHOVEL) {
			this.toolClass = "shovel";
			this.attackDamage = 1.5F + toolMaterial.getAttackDamage();
			this.attackSpeed = -3.0F;
		}		
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player,
			@Nullable IBlockState blockState) {
		if (blockState != null && blockState.getBlock().isToolEffective(this.toolClass, blockState)) {
			return this.toolMaterial.getHarvestLevel();
		}

		return super.getHarvestLevel(stack, toolClass, player, blockState);
	}

	@Override
	public boolean canHarvestBlock(IBlockState block) {
		return this.toolType.canHarvestBlock(this.toolMaterial, block);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState block) {
		for (String type : getToolClasses(stack)) {
			if (block.getBlock().isToolEffective(type, block))
				return efficiency;
		}

		return this.toolType.getDestroySpeed(stack, block) == 4.0F ? this.efficiency : 1.0F;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {
		if (Util.isValid(this.repairItem))
			return Util.areItemsEqual(this.repairItem, stack, false);
		else if (this.repairOreDict != null) {
			return Util.areItemsSameOreDict(this.repairOreDict, stack);
		}
		return false;
	}
	
    @Override
    public Set<String> getToolClasses(ItemStack stack){
        return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
    }
}
