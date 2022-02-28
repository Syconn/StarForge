package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.screen.componets.MutableSlider;
import mod.stf.syconn.common.containers.CrafterContainer;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageChangeColor;
import mod.stf.syconn.util.ColorConverter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class CrafterScreen extends AbstractContainerScreen<CrafterContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/lightsaber_forge.png");
    private CrafterContainer inv;

    private MutableSlider[] sliderColor = new MutableSlider[3];
    private EditBox[] textColor = new EditBox[3];
    private ExtendedButton saveButton;
    private boolean updateSliders = true;
    private int[] rgb = new int[sliderColor.length];
    private String[] lastTextValue = new String[textColor.length];

    private int relX;
    private int relY;

    public CrafterScreen(CrafterContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.inv = container;
        imageHeight = 182;
        imageWidth = 176;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;

        if (getData() != null) fillRect(relX + 75, relY + 6, imageWidth / 2, 20, rgb[0], rgb[1], rgb[2], 255);

        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        addRenderableWidget(sliderColor[0] = new MutableSlider(0, relX + 10, relY + 33, "R", 0, 255, 185, this::sliderShit));
        addRenderableWidget(sliderColor[1] = new MutableSlider(1, relX + 10, relY + 54, "G", 0, 255, 185, this::sliderShit));
        addRenderableWidget(sliderColor[2] = new MutableSlider(2, relX + 10, relY + 75, "B", 0, 255, 185, this::sliderShit));
        addRenderableWidget(textColor[0] = new EditBox(font, relX - 70, relY + 33, 60, 18, new TextComponent("R")));
        addRenderableWidget(textColor[1] = new EditBox(font, relX - 70, relY + 54, 60, 18, new TextComponent("G")));
        addRenderableWidget(textColor[2] = new EditBox(font, relX - 70, relY + 75, 60, 18, new TextComponent("B")));
        addRenderableWidget(saveButton = new ExtendedButton(relX + 30, relY + 6, 40, 20, new TextComponent("Modify"), this::buttonShit));
    }

    public void sliderShit(Button button){
        textColor[((MutableSlider)button).getId()].setValue(String.valueOf(((MutableSlider)button).getValueInt()));
        rgb[((MutableSlider)button).getId()] = ((MutableSlider)button).getValueInt();
        System.out.println("SCROLL");
    }

    public void buttonShit(Button button){
        Network.getPlayChannel().sendToServer(new MessageChangeColor(ColorConverter.convert(rgb[0], rgb[1], rgb[2])));
    }

    public IItemHandler handler(){
        LazyOptional<IItemHandler> cap = inv.getBlockEntity().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

        if (cap.isPresent()) {
            return cap.resolve().get();
        }

        return null;
    }

    @Nullable
    public LightsaberData getData(){
        IItemHandler handler = handler();
        if (handler.getStackInSlot(0) != ItemStack.EMPTY) {
            return LightsaberHelper.getData(handler.getStackInSlot(0));
        }

        return null;
    }

    public void updateSliders(){
        if (getData() != null){
            if (updateSliders) {
                sliderColor[0].setValue(getData().getColor().getR());
                sliderColor[1].setValue(getData().getColor().getG());
                sliderColor[2].setValue(getData().getColor().getB());
                sliderColor[0].updateSlider();
                sliderColor[1].updateSlider();
                sliderColor[2].updateSlider();
                updateSliders = false;
            }
        } else updateSliders = true;

        rgb[0] = sliderColor[0].getValueInt();
        rgb[1] = sliderColor[1].getValueInt();
        rgb[2] = sliderColor[2].getValueInt();
    }

    public void updateText(){
        for (int i = 0; i < sliderColor.length; i++) {
            int textNum;
            try {
                textNum = Integer.parseInt(textColor[i].getValue());
            } catch (NumberFormatException ignored){
                textColor[i].setValue(String.valueOf(1));
                textNum = 1;
            }

        }
    }

    @Override
    protected void containerTick() {
        updateText();
        updateSliders();

        if (handler() != null) {
            if (handler().getStackInSlot(0) != ItemStack.EMPTY) {
                for (int i = 0; i < sliderColor.length; i++) {
                    sliderColor[i].visible = true;
                    textColor[i].visible = true;
                }
                saveButton.visible = true;
            }

            else  {
                for (int i = 0; i < sliderColor.length; i++) {
                    sliderColor[i].visible = false;
                    textColor[i].visible = false;
                }
                saveButton.visible = false;
            }
        }
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().font, "Lightsaber Workstation", relX, relY - 10, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        //fill(matrixStack, relX, relY, relX + 100, relY + 40, 15435844);
    }

    private void fillRect(int pX, int pY, int pWidth, int pHeight, int pRed, int pGreen, int pBlue, int pAlpha) {
        BufferBuilder pRenderer = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        pRenderer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        pRenderer.vertex((double)(pX + 0), (double)(pY + 0), 0.0D).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        pRenderer.vertex((double)(pX + 0), (double)(pY + pHeight), 0.0D).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        pRenderer.vertex((double)(pX + pWidth), (double)(pY + pHeight), 0.0D).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        pRenderer.vertex((double)(pX + pWidth), (double)(pY + 0), 0.0D).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        pRenderer.end();
        BufferUploader.end(pRenderer);
    }
}