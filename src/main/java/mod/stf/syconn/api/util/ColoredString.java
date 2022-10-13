package mod.stf.syconn.api.util;

import net.minecraft.world.item.DyeColor;

public class ColoredString {

    private final String string;
    private final int color;

    public ColoredString(String string) {
        this.string = string;
        color = 16777215;
    }

    public ColoredString(String string, int color) {
        this.string = string;
        this.color = color;
    }

    public String getString() {
        return string;
    }

    public int getColor() {
        return color;
    }
}