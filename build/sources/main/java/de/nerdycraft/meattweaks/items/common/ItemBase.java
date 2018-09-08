package de.nerdycraft.meattweaks.items.common;

import de.nerdycraft.meattweaks.MeatTweaks;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    private final String name;

    public ItemBase(String name){
        this.name = name;
        
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
		setCreativeTab(MeatTweaks.tabMeatTweaks);
    }
    
    protected String getBaseName(){
        return this.name;
    }
}
