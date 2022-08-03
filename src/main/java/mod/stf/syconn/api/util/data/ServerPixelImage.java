package mod.stf.syconn.api.util.data;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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

    public ServerPixelImage(TextureAtlasSprite image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixels = getPixelFromSprite(image);
    }

    public ServerPixelImage(NativeImage background, DirectionalTexture... images) {
        this.height = background.getHeight();
        this.width = background.getWidth();
        this.pixels = createBlockTexture(background, images);
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

    public int[][] getPixelFromSprite(TextureAtlasSprite sprite){
        int[][] output = new int[sprite.getWidth()][sprite.getHeight()];
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getHeight(); y++) {
                output[x][y] = sprite.getPixelRGBA(0, x, y);
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

    public int[][] createBlockTexture(NativeImage background, DirectionalTexture... images){
        int[][] output = getPixelFromImage(background);
        for (int i = 0; i < images.length; i++){
            for (int x = 0; x < images[i].getTexture().getWidth(); x++) {
                for (int y = 0; y < images[i].getTexture().getHeight(); y++) {
                    output[x + images[i].x()][y + images[i].y()] = images[i].getTexture().getPixelRGBA(0, x, y);
                }
            }
        }
        return output;
    }
}
