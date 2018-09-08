package de.nerdycraft.meattweaks.proxy;

import de.nerdycraft.meattweaks.MeatTweaks;
import de.nerdycraft.meattweaks.init.ModBlocks;
import de.nerdycraft.meattweaks.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		MeatTweaks.instance.blocks = new ModBlocks();
		MeatTweaks.instance.items = new ModItems();
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(MeatTweaks.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	MeatTweaks.instance.blocks.registerBlocks(event.getRegistry());
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	MeatTweaks.instance.items.registerAll(event.getRegistry());
    	MeatTweaks.instance.blocks.registerBlockItems(event.getRegistry());
    }
}
