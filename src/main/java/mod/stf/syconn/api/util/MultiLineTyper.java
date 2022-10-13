package mod.stf.syconn.api.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;

import java.util.ArrayList;
import java.util.List;

public class MultiLineTyper {

    private final List<ColoredString> lines = new ArrayList<>();
    private int lineLng;
    private int lineTop;
    private int lineBottom;

    public MultiLineTyper(int lineLng, int t, int b) {
        this.lineLng = lineLng;
        this.lineTop = t;
        this.lineBottom = b;
    }

    public void render(PoseStack pStack, Font font, int x, int y, int topLine){
        int window = lineBottom - lineTop;
        int letterPixels = 10;
        int totLines = window / letterPixels;
        for (int i = topLine; i < topLine + totLines; i++) {
            if (size() > i) {
                GuiComponent.drawString(pStack, font, lines.get(i).getString(), x, y + ((i - topLine) * letterPixels), lines.get(i).getColor());
            }
        }
    }

    public void addLine(ColoredString msg){
        lines.addAll(addParagraph(msg));
    }

    public void addLine(String msg){
        lines.addAll(addParagraph(new ColoredString(msg)));
    }

    public int size(){
        return lines.size();
    }

    private List<ColoredString> addParagraph(ColoredString msg){
        List<ColoredString> lines = new ArrayList<>();
        String[] text = msg.getString().split(" ");
        int len = -1;
        String line = "";
        for (String str : text){
            len += str.length() + 1;
            if (len > lineLng){
                lines.add(new ColoredString(line, msg.getColor()));
                line = str;
                len = 0;
            } else {
                line += " " + str;
            }
        }
        lines.add(new ColoredString(line, msg.getColor()));
        return lines;
    }
}
