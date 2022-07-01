package mod.stf.syconn.item.lightsaber;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class LColor {

    private final int r;
    private final int g;
    private final int b;
    private int cycle = 0;
    private int timer = 0;

    public static LColor of(DyeColor color){
        return new LColor(color);
    }
    public static LColor of(Color color){
        return new LColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public LColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public LColor(DyeColor color){
        this(color.getFireworkColor());
    }

    public LColor(int color) {
        r = color >> 16 & 255;
        g = color >> 8 & 255;
        b = color & 255;
    }

    public LColor(int r, int g, int b, int cycle, int timer) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.cycle = cycle;
        this.timer = timer;
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

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int rainbow(ItemStack stack){
        DyeColor color = DyeColor.byId(cycle);
        int t = timer+=1;
        if (t >= 1000) {
            int i = cycle+=1;
            if (cycle >= 15) LightsaberHelper.setData(stack, LightsaberHelper.getData(stack).setColor(new LColor(r, b, g, 0, 0)));
            else LightsaberHelper.setData(stack, LightsaberHelper.getData(stack).setColor(new LColor(r, b, g, i, 0)));
        }
        else LightsaberHelper.setData(stack, LightsaberHelper.getData(stack).setColor(new LColor(r, b, g, cycle, t)));
        return color.getFireworkColor();
    }

    public static void save(CompoundTag tag, LColor color){
        tag.putInt("r", color.r);
        tag.putInt("g", color.g);
        tag.putInt("b", color.b);
        tag.putInt("cycle", color.cycle);
        tag.putInt("timer", color.timer);
    }

    public static LColor read(CompoundTag tag){
        return new LColor(tag.getInt("r"), tag.getInt("g"), tag.getInt("b"), tag.getInt("cycle"), tag.getInt("timer"));
    }
}
