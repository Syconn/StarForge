package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.componet.ToggleButton;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class MapScreen extends Screen {

    private final int imageWidth = 176;
    private final int imageHeight = 85;

    public MapScreen() {
        super(Component.literal("Map Screen"));
    }

    protected void init() {
        super.init();
        addRenderableWidget(new EditBox(font, 0, 0, 30, 20, Component.literal("X")));
        addRenderableWidget(new EditBox(font, 0, 21, 30, 20, Component.literal("Y")));
        addRenderableWidget(new EditBox(font, 0, 42, 30, 20, Component.literal("Z")));
        addRenderableWidget(new ExtendedButton(40, 21, 30, 20, Component.literal("Render"), this::renderMap));
        addRenderableWidget(new ToggleButton(40, 0, 40, 20, "Scale: ", "Normal", "Large", "Normal", this::toggle));
    }

    private void toggle(Button button) {

    }

    private void renderMap(Button button) {

    }


    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderBackground(pPoseStack);
    }

    public void renderBackground(PoseStack pPoseStack) {
        super.renderBackground(pPoseStack);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, new ResourceLocation(Reference.MOD_ID, "textures/gui/map.png"));
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
