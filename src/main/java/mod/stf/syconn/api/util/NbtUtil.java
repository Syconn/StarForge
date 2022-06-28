package mod.stf.syconn.api.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;

public class NbtUtil {

    public static CompoundTag writeNativeImage(NativeImage image){
        CompoundTag tag = new CompoundTag();
        tag.putInt("width", image.getWidth());
        tag.putInt("height", image.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (tag.contains(x+"_"+y)){
                    System.out.println(x+"_"+y + " - Contained: " + tag.getInt(x+"_"+y) + " Expected: " + image.getPixelRGBA(x, y));
                }

                tag.putInt(x + "_" + y, image.getPixelRGBA(x, y));
            }
        }

        return tag;
    }

    public static NativeImage readNativeImage(CompoundTag tag){
        NativeImage image = new NativeImage(tag.getInt("width"), tag.getInt("height"), false);

        for (int x = 0; x < tag.getInt("width"); x++) {
            for (int y = 0; y < tag.getInt("height"); y++) {
                image.setPixelRGBA(x, y, tag.getInt(x + "_" + y));
            }
        }

        return image;
    }
}
