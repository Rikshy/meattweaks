package de.nerdycraft.meattweaks;


import javax.annotation.Nonnull;

import org.apache.logging.log4j.Logger;

import de.nerdycraft.meattweaks.init.ModBlocks;
import de.nerdycraft.meattweaks.init.ModItems;
import de.nerdycraft.meattweaks.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION, useMetadata = true)
public class MeatTweaks {

    @SidedProxy(clientSide = Constants.CLIENT_PROXY, serverSide = Constants.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    public static MeatTweaks instance;

    public static Logger logger;
    
    public ModBlocks blocks;
    public ModItems items;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
   
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
        
    }
    
    public static final CreativeTabs tabMeatTweaks = new CreativeTabs(Constants.MOD_ID)
    {
        @Override
        @Nonnull
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Item.getItemFromBlock(Blocks.DIRT), 1, 0);
        }
    };
}
