package mod.stf.syconn.item.lightsaber;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;

import java.awt.*;

public class LColor {

    private final int r;
    private final int g;
    private final int b;

    public LColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public LColor(int color) {
        r = color >> 16 & 255;
        g = color >> 8 & 255;
        b = color & 255;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getDecimal(){
        int finalColor = (r << 8) + g;
        finalColor = (finalColor << 8) + b;

        return finalColor;
    }

    public DyeColor getClosetColor(){
        int closetNum = -1;
        //NEEDS INITIALIZATION
        DyeColor closetColor = DyeColor.WHITE;

        for (DyeColor color : DyeColor.values()){
            LColor lColor = new LColor(color.getFireworkColor());

            int checkNumR = Math.abs(getR() - lColor.getR());
            int checkNumG = Math.abs(getG() - lColor.getG());
            int checkNumB = Math.abs(getB() - lColor.getB());
            int checkNum = checkNumR + checkNumG + checkNumB;

            if (closetNum == -1) {
                closetNum = checkNum;
                closetColor = color;
            }
            //CHECKS SMALLEST DISTANCE VAL
            else if (closetNum > checkNum) {
                closetNum = checkNum;
                closetColor = color;
            }
        }

        return closetColor;
    }

    public static void save(CompoundTag tag, LColor color){
        tag.putInt("r", color.r);
        tag.putInt("g", color.g);
        tag.putInt("b", color.b);
    }

    public static LColor read(CompoundTag tag){
        return new LColor(tag.getInt("r"), tag.getInt("g"), tag.getInt("b"));
    }
}
