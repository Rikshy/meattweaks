package de.nerdycraft.meattweaks.gui;

import de.nerdycraft.meattweaks.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiTrashBlock extends GuiContainer {

    private static final ResourceLocation background = new ResourceLocation(Constants.MOD_ID, "textures/gui/trashblock_container.png");

    public GuiTrashBlock(ContainerTrashBlock container) {
        super(container);

        this.xSize = container.getGuiXSize();
        this.ySize = container.getGuiYSize();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
