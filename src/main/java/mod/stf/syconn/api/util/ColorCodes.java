package mod.stf.syconn.api.util;

import net.minecraft.world.level.material.MaterialColor;

public enum ColorCodes {
    DARK_RED(0, "§4", 11141120),
    RED(1, "§c", 16733525),
    GOLD(2, "§6", 16755200),
    YELLOW(3, "§e", 16777045),
    DARK_GREEN(4, "§2", 43520),
    GREEN(5, "§a", 5635925),
    AQUA(6, "§b", 5636095),
    DARK_AQUA(7, "§3", 43690),
    DARK_BLUE(8, "§1", 170),
    BLUE(9, "§9", 5592575),
    LIGHT_PURPLE(10, "§d", 16733695),
    DARK_PURPLE(11, "§5", 11141290),
    WHITE(12, "§f", 16777215),
    GRAY(13, "§7", 11184810),
    DARK_GRAY(14, "§8", 5592405),
    BLACK(15, "§0", 0);

    private final int id;
    private final String code;
    private final int color;

    ColorCodes(int id, String code, int color) {
        this.id = id;
        this.code = code;
        this.color = color;
    }

    public int getID() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getColor() {
        return color;
    }

    public ColorCodes getColorByCode(String code){
        for (ColorCodes color : ColorCodes.values()){
            if (color.code.equals(code)){
                return color;
            }
        }
        return null;
    }

    public ColorCodes getColorByID(int id){
        for (ColorCodes color : ColorCodes.values()){
            if (color.id == id){
                return color;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return code;
    }
}
