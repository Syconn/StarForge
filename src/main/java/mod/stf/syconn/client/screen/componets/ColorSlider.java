package mod.stf.syconn.client.screen.componets;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.util.GuiHelper;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ForgeSlider;

public class ColorSlider extends ForgeSlider {

    private final String value;
    private final int id;

    public ColorSlider(int id, int x, int y, String prefix, double min, double max, double current) {
        super(x, y, 255, 20, Component.empty(), Component.literal(prefix), min, max, current, true);
        this.value = prefix;
        this.id = id;
        updateMessage();
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        switch (id){
            case 0 -> GuiHelper.fillRect(getX(), getY(), width, height, getValueInt(), 0, 0, 255);
            case 1 -> GuiHelper.fillRect(getX(), getY(), width, height, 0, getValueInt(), 0, 255);
            case 2 -> GuiHelper.fillRect(getX(), getY(), width, height, 0, 0, getValueInt(), 255);
        }
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public ColorSlider hide(){
        visible = false;
        return this;
    }

    public void updateMessage() {
        super.updateMessage();
        setMessage(Component.literal(getValueInt() + " " + value));
    }

    public int getId() {
        return id;
    }
}
