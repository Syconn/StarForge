package mod.stf.syconn.api.util.applications;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.containers.ApplicationContainer;

public abstract class BasicApplication<T extends ApplicationContainer> {

    private T container;

    public BasicApplication(T container) {
        this.container = container;
    }

    public T getContainer() {
        return container;
    }

    abstract void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick);
    abstract void init();
    abstract void renderBG(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY);
}
