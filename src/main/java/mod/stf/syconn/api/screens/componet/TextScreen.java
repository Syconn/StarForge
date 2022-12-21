package mod.stf.syconn.api.screens.componet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.util.MultiLineTyper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.glfw.GLFW;

public class TextScreen extends AbstractWidget implements Widget, GuiEventListener {

    private static final ResourceLocation BAR = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");

    private final Font font = Minecraft.getInstance().font;
    private final int linesMax;
    private SubmittableTextBox textBox;
    public MultiLineTyper multiLineTyper = new MultiLineTyper(35, y, y + 148);
    private final int scrollBarBottom;
    private final int scrollBarX, scrollBarY;
    private float scrollOffs;
    private boolean scrolling;
    private int startingLine = 0;

    public TextScreen(int pX, int pY, int pWidth, int pHeight, int scrollBarBottom, int scrollBarX, int scrollBarY, String pMessage, SubmittableTextBox textBox) {
        super(pX, pY, pWidth, pHeight, new TextComponent(pMessage));
        linesMax = (pHeight - textBox.getHeight()) / 20;
        this.scrollBarBottom = scrollBarBottom;
        this.scrollBarX = scrollBarX;
        this.scrollBarY = scrollBarY;
        this.textBox = textBox;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        //SCROLLING PROBLEMS
        if (multiLineTyper.size() > 0) {
            multiLineTyper.render(pPoseStack, font, x, y, startingLine);
        }

        if (canScroll()) {
            RenderSystem.setShaderTexture(0, BAR);
            this.blit(pPoseStack, scrollBarX, (int) (scrollBarY + scrollOffs * (scrollBarBottom - 16)), 232 + (this.canScroll() ? 0 : 12), 0, 12, 15);
        }
    }

    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (insideScrollbar(pMouseX, pMouseY)) {
            scrolling = canScroll();
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (scrolling){
            scrolling = false;
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (!this.canScroll()) {
            return false;
        } else {
            startingLine = Mth.clamp((int) (startingLine - pDelta), 0, multiLineTyper.size());
            scrollOffs = (float) startingLine / multiLineTyper.size();
            return true;
        }
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == GLFW.GLFW_KEY_UP && textBox.getValue().matches("") && multiLineTyper.getInputLines().size() > 0){
            textBox.setValue(multiLineTyper.getInputLines().get(multiLineTyper.getInputLines().size() - 1));
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
    }

    protected boolean insideScrollbar(double pMouseX, double pMouseY) {
        int k = scrollBarX - 1;
        int l = scrollBarY - 1;
        int i1 = k + 14;
        int j1 = l + scrollBarBottom;
        return pMouseX >= (double)k && pMouseY >= (double)l && pMouseX < (double)i1 && pMouseY < (double)j1;
    }

    private boolean canScroll() {
        return multiLineTyper.size() > linesMax;
    }
}
