package mod.stf.syconn.client.screen.componets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.util.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.ScreenUtils;
import net.minecraftforge.client.gui.widget.ForgeSlider;

import java.util.function.Consumer;

public class ColorSlider extends ForgeSlider {

    private final String prefix;
    private final int id;
    private final Consumer<ColorSlider> consumer;

    public ColorSlider(int id, int x, int y, String prefix, double min, double max, double current, Consumer<ColorSlider> consumer) {
        super(x, y, 150, 20, Component.empty(), Component.literal(prefix), min, max, current, true);
        this.prefix = prefix;
        this.id = id;
        this.consumer = consumer;
        updateMessage();
    }

    public void renderWidget(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.visible) {
            switch (id){
                case 0 -> GuiHelper.fillRect(pPoseStack, getX(), getY(), width, height, getValueInt(), 0, 0, 255);
                case 1 -> GuiHelper.fillRect(pPoseStack, getX(), getY(), width, height, 0, getValueInt(), 0, 255);
                case 2 -> GuiHelper.fillRect(pPoseStack, getX(), getY(), width, height, 0, 0, getValueInt(), 255);
            }

            RenderSystem.setShaderTexture(0, SLIDER_LOCATION);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            blitNineSliced(pPoseStack, this.getX() + (int)(this.value * (double)(this.width - 8)), this.getY(), 8, 20, 20, 4, 200, 20, 0, this.getHandleTextureY());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            int i = this.active ? 16777215 : 10526880;
            this.renderScrollingString(pPoseStack, Minecraft.getInstance().font, 2, i | Mth.ceil(this.alpha * 255.0F) << 24);
        }
    }

    protected void applyValue() {
        consumer.accept(this);
    }

    public ColorSlider hide(){
        visible = false;
        return this;
    }

    public void updateMessage() {
        setMessage(Component.literal(prefix + " " + getValueInt()));
    }

    public int getId() {
        return id;
    }
}
