package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.BasicContainerScreen;
import mod.stf.syconn.common.containers.SchematicContainer;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.s2c.MessageLoadBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class SchematicScreen extends BasicContainerScreen<SchematicContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/schem.png");
    private int relX, relY;
    private final BlockPos pos;
    private int imageWidth = 176;
    private int imageHeight = 172;

    public SchematicScreen(SchematicContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.pos = container.getBlockEntity().getBlockPos();
    }

    @Override
    protected void init() {
        super.init();
        relX = (this.width - this.imageWidth) / 2 + imageWidth / 2 - 30;
        relY = (this.height - this.imageHeight) / 2 + 20;
        addRenderableWidget(new ExtendedButton(relX, relY + 45, 60, 20, Component.literal("Load Block"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageLoadBlock(pos));
            //onClose();
        }));
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
