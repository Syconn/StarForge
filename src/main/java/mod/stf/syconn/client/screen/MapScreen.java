package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.componet.ToggleButton;
import mod.stf.syconn.common.blockEntity.MapBe;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class MapScreen extends Screen {

    private final int imageWidth = 176;
    private final int imageHeight = 85;

    private final MapBe be;

    public MapScreen(MapBe be) {
        super(Component.literal("Map Screen"));
        this.be = be;
    }

    protected void init() {
        super.init();
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        for (BlockPos pos : be.getSelections()) {
            addRenderableWidget(new ExtendedButton(relX, relY, 40, 20, Component.literal(new ChunkPos(pos).toString()), this::toggle));
        }
        System.out.println(be.getSelections().size());
    }

    private void toggle(Button button) {

    }

    private void renderMap(Button button) {

    }


    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void renderBackground(PoseStack pPoseStack) {
        super.renderBackground(pPoseStack);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, new ResourceLocation(Reference.MOD_ID, "textures/gui/map.png"));
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
