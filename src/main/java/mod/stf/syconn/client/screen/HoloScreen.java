package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageHoloMode;
import mod.stf.syconn.network.messages.MessageSetupSkin;
import mod.stf.syconn.network.messages.MessageSlimSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
        super(new TextComponent("Holo Screen"));
        this.pos = pos;
        this.be = be;
    }

    @Override
    protected void init() {
        //TODO ACTUALLY LINK SERVER AND CLIENT

        super.init();
        relX = (this.width - this.imageWidth) / 2 + imageWidth / 2 - 30;
        relY = (this.height - this.imageHeight) / 2 + 20;
        addRenderableWidget(new ExtendedButton(relX - 18, relY, 100, 20, new TextComponent("Mode: " + be.getMode()), pButton -> {
            if (pButton.getMessage().getContents().matches("Mode: Username")) {
                pButton.setMessage(new TextComponent("Mode: URL"));
                text.setValue("URL");
                Network.getPlayChannel().sendToServer(new MessageHoloMode("URL", pos));
                slim.visible = true;
            } else {
                pButton.setMessage(new TextComponent("Mode: Username"));
                text.setValue("Username");
                Network.getPlayChannel().sendToServer(new MessageHoloMode("Username", pos));
                slim.visible = false;
            }
        }));
        addRenderableWidget(new ExtendedButton(relX, relY + 60, 60, 20, new TextComponent("Set Skin"), pButton -> {
            if (!text.getValue().contains(" ") && !text.getValue().matches("Username") && !text.getValue().matches("URL")) {
                Network.getPlayChannel().sendToServer(new MessageSetupSkin(text.getValue(), pos, slim.getMessage().getContents().equals("Slim")));
                onClose();
            }
        }));
        addRenderableWidget(text = new EditBox(font, relX - 30, relY + 30, 120, 20, new TextComponent(be.getUrlOrName())));
        addRenderableWidget(slim = new ExtendedButton(relX, relY + 85, 60, 20, new TextComponent(be.isSlim() ? "Slim" : "Standard"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageSlimSkin(pButton.getMessage().equals("Slim"), pos));
            if (pButton.getMessage().getContents().matches("Standard")){
                pButton.setMessage(new TextComponent("Slim"));
            } else {
                pButton.setMessage(new TextComponent("Standard"));
            }
        }));
        text.setValue(be.getUrlOrName());
        text.setMaxLength(1000000);

        if (be.getMode().equals("Username")){
            slim.visible = false;
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
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
