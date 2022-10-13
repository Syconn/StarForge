package mod.stf.syconn.client.screen.componets;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TextComponent;
import org.lwjgl.glfw.GLFW;

public class SubmittableTextBox extends EditBox {

    private String output = "";
    private boolean submitted = false;

    public SubmittableTextBox(Font pFont, int pX, int pY, int pWidth, int pHeight, String pMessage) {
        super(pFont, pX, pY, pWidth, pHeight, new TextComponent(pMessage));
    }

    protected void submit(){
        output = getValue();
        submitted = true;
        setValue("");
    }

    public boolean hasSubmitted(){
        return submitted;
    }

    public String getSubmission() {
        if (submitted) {
            submitted = false;
            return output;
        } return "";
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == GLFW.GLFW_KEY_ENTER && !submitted && !getValue().matches("")){
            submit();
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }
}
