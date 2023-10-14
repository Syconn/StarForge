package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.screen.componets.MutableTextBox;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.c2s.C2SSetName;
import mod.stf.syconn.network.messages.s2c.S2COpenProjector;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;

public class ProbeScreen extends Screen {

    private final BlockPos pos;
    private final Component currentName;
    private final int imageWidth = 152;
    private final int imageHeight = 68;

    private EditBox textBox;

    public ProbeScreen(BlockPos pos, Component currentName) {
        super(Component.literal("Probe Screen"));
        this.pos = pos;
        this.currentName = currentName;
    }

    protected void init() {
        super.init();
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        addRenderableWidget(textBox = new EditBox(font, relX + 25, relY + this.imageHeight / 2 - 11, 100, 20, currentName));
        textBox.setMaxLength(15);
        textBox.setValue(currentName.getString());
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void renderBackground(PoseStack pPoseStack) {
        super.renderBackground(pPoseStack);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, new ResourceLocation(Reference.MOD_ID, "textures/gui/textbox.png"));
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }

    public void onClose() {
        Network.getPlayChannel().sendToServer(new C2SSetName(pos, Component.literal(textBox.getValue())));
        super.onClose();
    }
}