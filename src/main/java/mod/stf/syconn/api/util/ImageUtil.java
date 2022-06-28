package mod.stf.syconn.api.util;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.item.lightsaber.LColor;
import net.minecraft.world.item.DyeColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageUtil {

    public static NativeImage tint(NativeImage image, DyeColor colorValue){
        NativeImage edited = new NativeImage(image.getWidth(), image.getHeight(), true);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getPixelRGBA(x, y), true);
                int r = pixelColor.getRed();
                int g = pixelColor.getGreen();
                int b = pixelColor.getBlue();
                int a = 160; //190
                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                edited.setPixelRGBA(x, y, rgba);
                pixelColor = new Color(image.getPixelRGBA(x, y), true);
                r = (pixelColor.getRed() + LColor.of(colorValue).getR()) / 2;
                g = (pixelColor.getGreen() + LColor.of(colorValue).getG()) / 2;
                b = (pixelColor.getBlue() + LColor.of(colorValue).getB()) / 2;
                a = 180;
                int rgba2 = (a << 24) | (r << 16) | (g << 8) | (b);
                edited.blendPixel(x, y, rgba2);
            }
        }
        return edited;
    }
}
