package mod.stf.syconn.api.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;

import java.util.ArrayList;
import java.util.List;

public class ColorFormattedLine {

    public static void renderLine(List<ColoredString> strings, PoseStack pStack, Font font, int x, int y, int topLine){
        for (ColoredString string : strings) {
            GuiComponent.drawString(pStack, font, lines.get(i).getString(), x, y + ((i - topLine) * letterPixels), lines.get(i).getColor());
        }
    }
}
