package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.screens.BasicContainerScreen;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.containers.ApplicationContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ApplicationScreen<T extends ApplicationContainer, G extends BasicApplication<T>> extends BasicContainerScreen<T> {

    private final G application;

    public ApplicationScreen(G application, Inventory pPlayerInventory, Component pTitle) {
        super(application.getContainer(), pPlayerInventory, pTitle);
        this.application = application;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
