package de.nerdycraft.meattweaks.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.oredict.OreDictionary;

public final class Util {
	
	public static final int WILDCARD = OreDictionary.WILDCARD_VALUE;
	
	
	public static boolean isValid(ItemStack stack){
    	if(stack == null) beedoo("Null ItemStack detected", stack);
        return !stack.isEmpty();
    }
	
	public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2, boolean checkWildcard){
        return isValid(stack1) && isValid(stack2) && (stack1.isItemEqual(stack2) || (checkWildcard && stack1.getItem() == stack2.getItem() && (stack1.getItemDamage() == Util.WILDCARD || stack2.getItemDamage() == Util.WILDCARD)));
    }
	
	public static boolean areItemsSameOreDict( String oreDict, ItemStack stack ) {
		int[] idsStack = OreDictionary.getOreIDs(stack);
        for(int id : idsStack){
            if(OreDictionary.getOreName(id).equals(oreDict)){
                return true;
            }
        }
        return false;
	}
	
	public static void beedoo(Object... stuff) {
    	int i = 0;
    	String error = "MEAT Tweaks: Something is very wrong.  This method was provided with ";
    	for(Object k : stuff) {
    		error += ("\n" + i++ + ": " + (k == null ? "null" : (k.getClass().getSimpleName() + " <- CLASS | INSTANCE -> " + k.toString() + ", ")));
    	}
    	error += "\n" + "The current side is: " + FMLCommonHandler.instance().getEffectiveSide();
    	throw new IllegalStateException(error);
    }
}
