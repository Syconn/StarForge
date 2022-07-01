package mod.stf.syconn.api.util;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.item.lightsaber.LColor;
import net.minecraft.world.item.DyeColor;

import java.awt.*;

public class ImageUtil {

    public static NativeImage tint(NativeImage image, DyeColor colorValue){
        NativeImage edited = new NativeImage(image.getWidth(), image.getHeight(), true);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getPixelRGBA(x, y), true);
                if (image.getPixelRGBA(x, y) != 0) {
                    LColor tint = new LColor(colorValue);
                    Color c = pixelColor.brighter().brighter();
                    int tint_factor = 3;
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = (c.getBlue() + tint.getB());
                    int a = 160; //190
                    int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                    edited.setPixelRGBA(x, y, rgba);
                }
            }
        }
        return edited;
    }
    public static NativeImage tintV2(NativeImage image, DyeColor colorValue){
        NativeImage edited = new NativeImage(image.getWidth(), image.getHeight(), true);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getPixelRGBA(x, y), true);
                if (LColor.of(pixelColor).getClosetColor() != DyeColor.BLACK) {
                    int r = pixelColor.getRed();
                    int g = pixelColor.getGreen();
                    int b = pixelColor.getBlue();
                    int a = 160; //190
                    int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                    edited.setPixelRGBA(x, y, rgba);
                }
            }
        }
        return edited;
    }

    public static int getTintBlue(Color inColor){
        LColor color = new LColor(inColor.getRed(), inColor.getGreen(), inColor.getBlue());
        int a = 160;
        LColor tint = color;

        System.out.println(color.getClosetColor().getName());
        switch (color.getClosetColor()){
            case WHITE -> tint = new LColor(78, 176, 248);
            case YELLOW -> tint = new LColor(73, 165, 212);
            case LIGHT_BLUE -> tint = new LColor(42, 154, 249);
            case BLUE -> tint = new LColor(28, 100, 164);
            case RED -> tint = new LColor(62, 145, 213);
            case PINK -> tint = new LColor(81, 166, 230);
            case BROWN -> tint = new LColor(34, 100, 153);
            case PURPLE -> tint = new LColor(79, 135, 242);
            case LIME -> tint = new LColor(78, 182, 195);
            case BLACK -> tint = new LColor(34, 121, 188);
            case ORANGE -> tint = new LColor(127, 150, 150);
            case MAGENTA -> tint = new LColor(127, 93, 195);
            case GREEN -> tint = new LColor(54, 163, 150);
            case CYAN -> tint = new LColor(39, 159, 255);
            case LIGHT_GRAY -> tint = new LColor(75, 146, 202);
            case GRAY -> tint = new LColor(49, 119, 176);
        }
        return (a << 24) | (tint.getR() << 16) | (tint.getG() << 8) | tint.getB();
    }
}
