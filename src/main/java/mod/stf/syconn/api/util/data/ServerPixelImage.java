package mod.stf.syconn.api.util.data;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ServerPixelImage {

    private int height;
    private int width;
    private int[][] pixels;

    public ServerPixelImage(NativeImage image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixels = getPixelFromImage(image);
    }

    public ServerPixelImage(int height, int width, int[][] pixels) {
        this.height = height;
        this.width = width;
        this.pixels = pixels;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public int[][] getPixelFromImage(NativeImage image){
        int[][] output = new int[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                output[x][y] = image.getPixelRGBA(x, y);
            }
        }
        return output;
    }

    public NativeImage getImageFromPixels(){
        NativeImage output = new NativeImage(width, height, true);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                output.setPixelRGBA(x, y, pixels[x][y]);
            }
        }
        return output;
    }
}
