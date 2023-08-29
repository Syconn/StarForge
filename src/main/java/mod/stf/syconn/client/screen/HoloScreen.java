package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.BasicContainerScreen;
import mod.stf.syconn.api.screens.componet.ToggleButton;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.common.containers.HoloContainer;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.s2c.MessageHoloMode;
import mod.stf.syconn.network.messages.s2c.MessageResetHolo;
import mod.stf.syconn.network.messages.s2c.MessageSetupSkin;
import mod.stf.syconn.network.messages.s2c.MessageSlimSkin;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class HoloScreen extends BasicContainerScreen<HoloContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/holo.png");
    private int relX, relY;
    private final BlockPos pos;
    private final HoloBE be;
    private final int imageWidth = 176;
    private final int imageHeight = 216;
    private EditBox text;
    private ExtendedButton slim;

    public HoloScreen(HoloContainer container, Inventory inv, Component name) {
        super(container, inv, Component.empty());
        this.pos = container.getBlockEntity().getBlockPos();
        this.be = (HoloBE) container.getBlockEntity();
    }

    protected void init() {
        super.init();
        relX = (this.width - this.imageWidth) / 2 + imageWidth / 2 - 30;
        relY = (this.height - this.imageHeight) / 2 + 20;
        addRenderableWidget(new ToggleButton(relX - 18, relY, 100, 20, "Mode: ", "Username", "URL", be.getMode(), pButton -> {
            text.setValue(((ToggleButton) pButton).current);
            Network.getPlayChannel().sendToServer(new MessageHoloMode(((ToggleButton) pButton).current, pos));
            slim.visible = ((ToggleButton) pButton).current.matches("URL");
        }));
        addRenderableWidget(new ExtendedButton(relX, relY + 60, 60, 20, Component.literal("Set Skin"), pButton -> {
            if (!text.getValue().contains(" ") && !text.getValue().matches("Username") && !text.getValue().matches("URL")) {
                Network.getPlayChannel().sendToServer(new MessageSetupSkin(text.getValue(), pos, slim.getMessage().getContents().equals("Slim")));
                onClose();
            }
        }));
        addRenderableWidget(text = new EditBox(font, relX - 30, relY + 30, 120, 20, Component.literal(be.getUrlOrName())));
        addRenderableWidget(slim = new ToggleButton(relX, relY + 85, 60, 20, "", "Standard", "Slim", be.isSlim() ? "Slim" : "Standard", pButton -> Network.getPlayChannel().sendToServer(new MessageSlimSkin(pButton.getMessage().equals("Slim"), pos))));
        if (Reference.DEV_MODE) addRenderableWidget(new ExtendedButton(0, 0, 60, 20, Component.literal("RESET"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageResetHolo(pos));
        }));

        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;

        text.setValue(be.getUrlOrName());
        text.setMaxLength(1000000);

        if (be.getMode().equals("Username")){
            slim.visible = false;
        }
    }

    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) { }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBackground(pPoseStack);
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
