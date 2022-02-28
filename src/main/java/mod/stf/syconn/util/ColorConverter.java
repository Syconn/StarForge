package mod.stf.syconn.util;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.DyeColor;

public class ColorConverter {

    public static ChatFormatting convert(DyeColor color){
        switch (color){
            case RED -> { return ChatFormatting.DARK_RED; }
            case BLUE ->{ return ChatFormatting.DARK_BLUE; }
            case CYAN -> { return ChatFormatting.AQUA; }
            case GRAY -> { return ChatFormatting.DARK_GRAY; }
            case LIME -> { return ChatFormatting.GREEN; }
            case PINK -> { return ChatFormatting.RED; }
            case GREEN -> { return ChatFormatting.DARK_GREEN; }
            case WHITE -> { return ChatFormatting.WHITE; }
            case ORANGE -> { return ChatFormatting.GOLD; }
            case PURPLE -> { return ChatFormatting.DARK_PURPLE; }
            case YELLOW -> { return ChatFormatting.YELLOW; }
            case MAGENTA -> { return ChatFormatting.LIGHT_PURPLE; }
            case LIGHT_BLUE -> { return ChatFormatting.BLUE; }
            case LIGHT_GRAY -> { return ChatFormatting.GRAY; }
            default -> { return ChatFormatting.BLACK; }
        }
    }

    public static int convert(int r, int g, int b){
        int finalColor = (r << 8) + g;
        return  (finalColor << 8) + b;
    }
}
