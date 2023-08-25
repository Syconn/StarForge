package mod.stf.syconn.api.screens.componet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.Tab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class TabButton extends ExtendedButton {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/tabs.png");
    private final State state;
    private final Tab tab;
    private boolean selected = false;

    public TabButton(int x, int y, State state, Tab tab, OnPress onPress) {
        super(x, y - 26, 28, 28, null, onPress);
        this.state = state;
        this.tab = tab;
    }

    public void render(PoseStack mStack, int mouseX, int mouseY, float pPartialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        if (!selected){
            if (state == State.LEFT) blit(mStack, getX(), getY(), 0, 0, 28, 28);
            if (state == State.MIDDLE) blit(mStack, getX(), getY(), 28, 0, 28, 28);
            if (state == State.RIGHT) blit(mStack, getX(), getY(), 56, 0, 28, 28);
        } else {
            if (state == State.LEFT) blit(mStack, getX(), getY() - 2, 0, 30, 28, 31);
            if (state == State.MIDDLE) blit(mStack, getX(), getY() - 2, 28, 30, 28, 31);
            if (state == State.RIGHT) blit(mStack, getX(), getY() - 2, 56, 30, 28, 31);
        }

        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(mStack, new ItemStack(tab.icon()), getX() + 6, getY() + 6);

        if (isMouseOver(mouseX, mouseY))
            drawString(mStack, Minecraft.getInstance().font, Component.literal(tab.name()), getX() - 10, getY() - 10, 14737632);
    }

    public int getId() {
        return tab.id();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public enum State {
        LEFT,
        RIGHT,
        MIDDLE
    }
}