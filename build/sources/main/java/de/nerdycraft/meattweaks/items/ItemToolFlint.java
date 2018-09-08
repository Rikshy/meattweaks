package de.nerdycraft.meattweaks.items;


import de.nerdycraft.meattweaks.items.common.ItemToolBase;
import de.nerdycraft.meattweaks.util.EnumToolType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemToolFlint extends ItemToolBase {

	public ItemToolFlint(String unlocalizedName, EnumToolType toolType) {
		super(unlocalizedName, ToolMaterial.STONE, new ItemStack(Items.FLINT), toolType);
	}
}
