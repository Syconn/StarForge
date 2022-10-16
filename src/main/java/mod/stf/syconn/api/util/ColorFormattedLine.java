package mod.stf.syconn.api.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;

import java.util.ArrayList;
import java.util.List;

public class ColorFormattedLine {

    public static final int letterPixels = 10;

    public static void renderLine(List<ColoredString> strings, int y, PoseStack pStack, Font font, int x, int yPos, int topLine){
        int l = 0;
        for (ColoredString str : strings) {
            for (int i = 0; i < str.getString().length(); i++) {
                GuiComponent.drawString(pStack, font, str.getString().charAt(i) + "", x + l, yPos + ((y - topLine) * letterPixels), str.getColor());
                l+=getCharacterLength(str.getString().charAt(i) + "") + 1;
            }
            l+=2;
        }
    }

    public static int getCharacterLength(String s){
        if (s.matches("I") || s.matches("]") || s.matches("}") || s.matches("t")){
            return 3;
        } else if (s.matches(":") || s.matches("!") || s.matches("i")) {
            return 1;
        }
        return 5;
    }
}
