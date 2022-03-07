package mod.stf.syconn.api.screens.componet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.api.util.Tab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class TabButton extends ExtendedButton {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/tabs.png");
    private State state;
    private Tab tab;
    private boolean selected = false;

    public TabButton(int x, int y, State state, Tab tab, OnPress onPress) {
        super(x, y - 26, 28, 28, null, onPress);
        this.state = state;
        this.tab = tab;
    }

    @Override
    public void renderButton(PoseStack mStack, int mouseX, int mouseY, float partial) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (!selected){
            if (state == State.LEFT) blit(mStack, x, y, 0, 0, 28, 28);
            if (state == State.MIDDLE) blit(mStack, x, y, 28, 0, 28, 28);
            if (state == State.RIGHT) blit(mStack, x, y, 56, 0, 28, 28);
        } else {
            if (state == State.LEFT) blit(mStack, x, y - 1, 0, 30, 28, 27 + 3);
            if (state == State.MIDDLE) blit(mStack, x, y - 2, 28, 30, 28, 27 + 3);
            if (state == State.RIGHT) blit(mStack, x, y - 2, 58, 30, 28, 27 + 2);
        }

        //Minecraft.getInstance().getItemRenderer().renderAndDecorateItem();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static enum State {
        LEFT,
        RIGHT,
        MIDDLE
    }
}