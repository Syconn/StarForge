package mod.stf.syconn.api.util;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

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

    public static ServerPixelImage readServerImage(CompoundTag tag){
        int pixels[][] = new int[tag.getInt("width")][tag.getInt("height")];
        for (int x = 0; x < tag.getInt("width"); x++) {
            for (int y = 0; y < tag.getInt("height"); y++) {
                pixels[x][y] = tag.getInt(x + "_" + y);
            }
        }

        return new ServerPixelImage(tag.getInt("width"), tag.getInt("height"), pixels);
    }

    public static CompoundTag writeServerImage(ServerPixelImage image){
        CompoundTag tag = new CompoundTag();
        tag.putInt("width", image.getWidth());
        tag.putInt("height", image.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                tag.putInt(x + "_" + y, image.getPixels()[x][y]);
            }
        }

        return tag;
    }

    public static HashMap<BlockID, ServerPixelImage> readServerImageList(CompoundTag tag){
        HashMap<BlockID, ServerPixelImage> map = new HashMap<>();
        if(tag.contains("map", Tag.TAG_LIST))
        {
            ListTag list = tag.getList("map", Tag.TAG_COMPOUND);
            list.forEach(data ->
            {
                CompoundTag nbt = (CompoundTag) data;
                int width = nbt.getInt("width");
                int height = nbt.getInt("height");
                int pixels[][] = new int[width][height];
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        pixels[x][y] = nbt.getInt(x + "_" + y);
                    }
                }
                map.put(BlockID.read(nbt.getCompound("id")), new ServerPixelImage(width, height, pixels));
            });
        }

        return map;
    }

    public static CompoundTag writeServerImageList(HashMap<BlockID, ServerPixelImage> images){
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();

        images.forEach((id, image) -> {
            CompoundTag data = new CompoundTag();
            data.putInt("width", image.getWidth());
            data.putInt("height", image.getHeight());
            data.put("id", id.write());
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    data.putInt(x + "_" + y, image.getPixels()[x][y]);
                }
            }
            listTag.add(data);
        });
        tag.put("map", listTag);
        return tag;
    }

    public static CompoundTag writePositions(Map<BlockID, double[]> position) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        position.forEach(((id, doubles) -> {
            CompoundTag data = new CompoundTag();
            data.put("id", id.write());
            data.putInt("len", doubles.length);
            for (int i = 0; i < doubles.length; i++){
                data.putDouble(String.valueOf(i), doubles[i]);
            }
            listTag.add(data);
        }));
        tag.put("map", listTag);
        return tag;
    }

    public static Map<BlockID, double[]> readPositions(CompoundTag tag){
        Map<BlockID, double[]> map = new HashMap<>();
        if(tag.contains("map", Tag.TAG_LIST))
        {
            ListTag list = tag.getList("map", Tag.TAG_COMPOUND);
            list.forEach(data -> {
                CompoundTag nbt = (CompoundTag) data;
                double[] doubles = new double[nbt.getInt("len")];
                for (int i = 0; i < doubles.length; i++){
                    doubles[i] = nbt.getInt(String.valueOf(i));
                }
                map.put(BlockID.read(nbt.getCompound("id")), doubles);
            });
        }
        return map;
    }
}
