package mod.stf.syconn.client.screen.componets;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class MutableTextBox extends EditBox {

    public MutableTextBox(Font pFont, int pX, int pY, int pWidth, int pHeight, String pMessage) {
        super(pFont, pX, pY, pWidth, pHeight, new TextComponent(pMessage));
    }

    @Override
    public void onRelease(double pMouseX, double pMouseY) {
        super.onRelease(pMouseX, pMouseY);
    }
}
