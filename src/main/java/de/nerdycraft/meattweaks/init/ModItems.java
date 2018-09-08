package de.nerdycraft.meattweaks.init;

import java.util.ArrayList;

import de.nerdycraft.meattweaks.Config;
import de.nerdycraft.meattweaks.items.ItemToolFlint;
import de.nerdycraft.meattweaks.items.common.ItemBase;
import de.nerdycraft.meattweaks.util.EnumToolType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	private ArrayList<Item> items = new ArrayList<Item>();
    
    public ModItems() {
    	items.add(new ItemToolFlint("flint_axe", EnumToolType.AXE));
    	items.add(new ItemToolFlint("flint_shovel", EnumToolType.SHOVEL));
    	items.add(new ItemToolFlint("flint_pick", EnumToolType.PICKAXE));

    	Item plantFiber = new ItemBase("plant_fiber");
    	items.add(plantFiber);
		if(Config.MTConfig.fiberWeight > 0)
			MinecraftForge.addGrassSeed(new ItemStack(plantFiber), Config.MTConfig.fiberWeight);
		
		//trashBlock = new ItemTrashBlock(MTBlocks.trashBlock);
    }
	
	public void registerAll(IForgeRegistry<Item> reg)	{
		for (Item i : items ) {
			reg.register(i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void initModels() {
		for (Item i : items ) {
			registerModel(i, 0, "inventory");			
		}
	}	

    private void registerModel( Item item, int meta, String id) {
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
