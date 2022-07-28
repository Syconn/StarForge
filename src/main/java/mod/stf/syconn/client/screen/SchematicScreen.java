package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.componet.ToggleButton;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.*;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class SchematicScreen extends Screen {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/holo.png");
    private int relX, relY;
    private final BlockPos pos;
    private final HoloBE be;
    private int imageWidth = 176;
    private int imageHeight = 133;
    private EditBox text;

    public SchematicScreen(HoloBE be, BlockPos pos) {
        super(new TextComponent("Schematic Screen"));
        this.pos = pos;
        this.be = be;
    }

    @Override
    protected void init() {
        super.init();
        relX = (this.width - this.imageWidth) / 2 + imageWidth / 2 - 30;
        relY = (this.height - this.imageHeight) / 2 + 20;
        addRenderableWidget(new ExtendedButton(relX, relY + 60, 60, 20, new TextComponent("Load Block"), pButton -> {
            if (!text.getValue().contains(" ") && !text.getValue().equals("Block Name")) {
                //Network.getPlayChannel().sendToServer(new MessageLoadBlock(text.getValue(), pos));
                onClose();
            }
        }));
        addRenderableWidget(text = new EditBox(font, relX - 30, relY + 30, 120, 20, new TextComponent("Block Name")));

        text.setValue(be.getUrlOrName());
        text.setMaxLength(1000000);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        drawString(pPoseStack, font, "Inventory", relX - 60, relY + 10, 16777215);
    }

    @Override
    public void renderBackground(PoseStack pPoseStack) {
        super.renderBackground(pPoseStack);
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
