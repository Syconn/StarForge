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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class HoloScreen extends Screen {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/holo.png");
    private int relX, relY;
    private final BlockPos pos;
    private final HoloBE be;
    private int imageWidth = 176;
    private int imageHeight = 133;
    private EditBox text;
    private ExtendedButton slim;

    public HoloScreen(HoloBE be, BlockPos pos) {
        super(Component.literal("Holo Screen"));
        this.pos = pos;
        this.be = be;
    }

    @Override
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
        addRenderableWidget(slim = new ToggleButton(relX, relY + 85, 60, 20, "", "Standard", "Slim", be.isSlim() ? "Slim" : "Standard", pButton -> {
            Network.getPlayChannel().sendToServer(new MessageSlimSkin(pButton.getMessage().equals("Slim"), pos));
        }));
        if (Reference.DEV_MODE) addRenderableWidget(new ExtendedButton(0, 0, 60, 20, Component.literal("RESET"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageResetHolo(pos));
        }));

        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        addRenderableWidget(new ExtendedButton(relX - 59, relY + 20, 20, 20, Component.literal("M"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageHoloGear("M", pos));
        }));
        addRenderableWidget(new ExtendedButton(relX - 31, relY + 20, 20, 20, Component.literal("O"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageHoloGear("O", pos));
        }));
        addRenderableWidget(new ExtendedButton(relX - 45, relY + 42, 20, 20, Component.literal("A"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageHoloGear("A", pos));
        }));

        text.setValue(be.getUrlOrName());
        text.setMaxLength(1000000);

        if (be.getMode().equals("Username")){
            slim.visible = false;
        }
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

    @Override
    public void tick() {
        super.tick();
    }
}
