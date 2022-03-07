package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.common.containers.HiltContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class HiltScreen extends AbstractContainerScreen<HiltContainer> {

    public HiltScreen(HiltContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
