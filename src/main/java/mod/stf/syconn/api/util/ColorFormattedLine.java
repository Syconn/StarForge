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
            //TODO NEED TO ADD PIXEL FOR EVERY LETTER AND SYMBOL

            for (int i = 0; i < str.getString().length(); i++) {
                GuiComponent.drawString(pStack, font, str.getString().charAt(i) + "", x + (l * 7), yPos + ((y - topLine) * letterPixels), str.getColor());
                l++;
            }
        }
    }
}
