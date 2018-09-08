package de.nerdycraft.meattweaks.events;

import de.nerdycraft.meattweaks.Config.MTConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber()
public class BlockEventHandler {
	
	private static boolean isPlayerValid(EntityPlayer player) {

		// Explosions, Quarries, etc. all are ok to break blocks
		if (player == null || player instanceof FakePlayer) {
			return false;
		}
		// Always allow creative mode
		if (player.capabilities.isCreativeMode) {
			return false;
		}
		return true;
	}

	// Controls the drops of any block that is broken to require specific tools.
	@SubscribeEvent
	public static void harvestDrops(BlockEvent.HarvestDropsEvent event) {

		EntityPlayer player = event.getHarvester();

		if (event.getWorld().isRemote || !isPlayerValid(player)) {
			return;
		}

		Block block = event.getState().getBlock();

		// Leaves now drop sticks 20% without a knife. 50% with a knife
		if (block instanceof BlockLeaves) {
			harvestLeaves(event);
			return;
		}
	}

	
	@SubscribeEvent
	public static void breakSpeed(final PlayerEvent.BreakSpeed event) {
		EntityPlayer player = event.getEntityPlayer();

		if (!isPlayerValid(player)) {
			return;
		}
		
		Block block = event.getState().getBlock();
		
		if (block instanceof BlockLog) {
			harvestLogs(event);
			return;
		}
	}
	
	private static void harvestLeaves(BlockEvent.HarvestDropsEvent event) {
		if (MTConfig.sticksFromLeaves > 0) {
			float stickDropChance = (MTConfig.sticksFromLeaves / 100f);
			if (event.getWorld().rand.nextFloat() <= stickDropChance) {
				event.getDrops().add(new ItemStack(Items.STICK, 1));
			}
		}		
	}
	
	private static void harvestLogs(final PlayerEvent.BreakSpeed event) {
		EntityPlayer player = event.getEntityPlayer();
		if ( MTConfig.noPunching && !canToolHarvestBlock(event.getState(), player.getHeldItemMainhand(), player) )
			event.setCanceled(true);
	}
	
	private static boolean canToolHarvestBlock(final IBlockState state, final ItemStack stack, final EntityPlayer player) {
		final String tool = state.getBlock().getHarvestTool(state);
		return !stack.isEmpty() && tool != null
				&& stack.getItem().getHarvestLevel(stack, tool, player, state) >= state.getBlock().getHarvestLevel(state);
	}

}
