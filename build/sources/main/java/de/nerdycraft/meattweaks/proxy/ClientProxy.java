package de.nerdycraft.meattweaks.proxy;

import de.nerdycraft.meattweaks.MeatTweaks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static StateMapperBase propertyStringMapper = new StateMapperBase() {

		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return new ModelResourceLocation("minecraft:air");
		}
	};
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	MeatTweaks.instance.items.initModels();
    	MeatTweaks.instance.blocks.initModels();
    }
}
