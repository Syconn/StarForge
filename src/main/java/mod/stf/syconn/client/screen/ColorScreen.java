package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.TabbedScreen;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.client.screen.componets.ColorSlider;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageChangeColor;
import mod.stf.syconn.network.messages.MessageClickTab;
import mod.stf.syconn.util.ColorConverter;
import mod.stf.syconn.util.GuiHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ColorScreen extends TabbedScreen<ColorContainer> {


    //TODO REDO UPDATING OF SLIDER KINDA FUCKED POST UPDATE
    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/lightsaber_forge.png");
    private ColorContainer inv;

    private ColorSlider[] sliderColor = new ColorSlider[3];
    private EditBox[] textColor = new EditBox[3];
    private ExtendedButton saveButton;
    private boolean updateSliders = true;
    private int relX, relY;
    private int[] rgb = new int[sliderColor.length];
    private String[] lastTextValue = new String[textColor.length];

    public ColorScreen(ColorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.inv = container;
        imageHeight = 182;
        imageWidth = 176;
    }

    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        if (getData() != null) GuiHelper.fillRect(relX + 75, relY + 6, imageWidth / 2, 20, rgb[0], rgb[1], rgb[2], 255);

        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void init() {
        super.init();
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        addRenderableWidget(sliderColor[0] = new ColorSlider(0, relX + 10, relY + 33, "R", 0, 255, 185).hide());
        addRenderableWidget(sliderColor[1] = new ColorSlider(1, relX + 10, relY + 54, "G", 0, 255, 185).hide());
        addRenderableWidget(sliderColor[2] = new ColorSlider(2, relX + 10, relY + 75, "B", 0, 255, 185).hide());
        addRenderableWidget(textColor[0] = new EditBox(font, relX - 70, relY + 33, 60, 18, Component.literal("R")));
        addRenderableWidget(textColor[1] = new EditBox(font, relX - 70, relY + 54, 60, 18, Component.literal("G")));
        addRenderableWidget(textColor[2] = new EditBox(font, relX - 70, relY + 75, 60, 18, Component.literal("B")));
        addRenderableWidget(saveButton = new ExtendedButton(relX + 30, relY + 6, 40, 20, Component.literal("Modify"), this::buttonShit));
    }

    protected int startingTabId() {
        return LightsaberCrafter.States.COLOR.getId();
    }

    public void tabbedClicked(Button button) {
        super.tabbedClicked(button);
        Network.getPlayChannel().sendToServer(new MessageClickTab(((TabButton)button).getId(), inv.getBlockEntity().getBlockPos()));
    }

    public void buttonShit(Button button){
        Network.getPlayChannel().sendToServer(new MessageChangeColor(ColorConverter.convert(rgb[0], rgb[1], rgb[2])));
    }

    public IItemHandler handler(){
        LazyOptional<IItemHandler> cap = inv.getBlockEntity().getCapability(ForgeCapabilities.ITEM_HANDLER);

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
                textColor[0].setValue(Integer.toString(getData().getColor().getR()));
                textColor[1].setValue(Integer.toString(getData().getColor().getG()));
                textColor[2].setValue(Integer.toString(getData().getColor().getB()));
                sliderColor[0].updateMessage();
                sliderColor[1].updateMessage();
                sliderColor[2].updateMessage();
                updateSliders = false;
            }
        } else updateSliders = true;

        rgb[0] = sliderColor[0].getValueInt();
        rgb[1] = sliderColor[1].getValueInt();
        rgb[2] = sliderColor[2].getValueInt();
    }

    public void updateText(){
        for (int i = 0; i < textColor.length; i++) {
            if (textColor[i] != null) {
                int textNum;
                try {
                    textNum = Integer.parseInt(textColor[i].getValue());
                } catch (NumberFormatException ignored){
                    textColor[i].setValue(String.valueOf(1));
                    textNum = 1;
                }

                if (sliderColor[i].getValueInt() != textNum){
                    sliderColor[i].setValue(textNum);
                    sliderColor[i].updateMessage();
                }
            }
        }
    }

    @Override
    protected void containerTick() {
        hiltTick();
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        drawCenteredString(matrixStack, font, new LColor(rgb[0], rgb[1], rgb[2]).getClosetColor().getName().toUpperCase(), 120, 13, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void hiltTick(){
        if (sliderColor[0] != null) {
            updateText();
            updateSliders();

            if (handler() != null) {
                if (handler().getStackInSlot(0) != ItemStack.EMPTY) {
                    for (int i = 0; i < sliderColor.length; i++) {
                        sliderColor[i].visible = true;
                        textColor[i].visible = true;
                    }
                    saveButton.visible = true;
                } else {
                    for (int i = 0; i < sliderColor.length; i++) {
                        sliderColor[i].visible = false;
                        textColor[i].visible = false;
                    }
                    saveButton.visible = false;
                }
            }
        }
    }

    protected List<Tab> createTabs(){
        List<Tab> tabs = new ArrayList<>();
        for (LightsaberCrafter.States state : LightsaberCrafter.States.values()){
            tabs.add(new Tab(state.getId(), state.icon(), "tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + state.getSerializedName()));
        }
        return tabs;
    }
}