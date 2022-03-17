package mod.stf.syconn.api.screens.componet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.api.util.Tab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
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
            if (state == State.LEFT) blit(mStack, x, y - 2, 0, 30, 28, 31);
            if (state == State.MIDDLE) blit(mStack, x, y - 2, 28, 30, 28, 31);
            if (state == State.RIGHT) blit(mStack, x, y - 2, 56, 30, 28, 31);
        }

        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(new ItemStack(tab.icon()), x + 6, y + 6);

        if (isMouseOver(mouseX, mouseY))
            drawString(mStack, Minecraft.getInstance().font, new TranslatableComponent(tab.name()), x - 10, y - 5, 14737632);
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