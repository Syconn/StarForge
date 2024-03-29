package mod.stf.syconn.api.screens.componet;

import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class ToggleButton extends ExtendedButton {

    public String prefix;
    public String state1;
    public String state2;
    public String current;

    public ToggleButton(int xPos, int yPos, int width, int height, String prefix, String state1, String state2, String current, OnPress handler) {
        super(xPos, yPos, width, height, Component.literal(prefix + current), handler);
        this.prefix = prefix;
        this.state1 = state1;
        this.state2 = state2;
        this.current = current;
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (getMessage().getString().equals(prefix + state1)){
            setMessage(Component.literal(prefix + state2));
            current = state2;
        } else {
            setMessage(Component.literal(prefix + state1));
            current = state1;
        }
        super.onClick(pMouseX, pMouseY);
    }
}
