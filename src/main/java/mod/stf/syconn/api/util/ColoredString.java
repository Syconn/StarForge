package mod.stf.syconn.api.util;

public class ColoredString {

    private String string;
    private int color;

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