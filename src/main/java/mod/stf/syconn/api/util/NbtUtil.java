package mod.stf.syconn.api.util;

import mod.stf.syconn.api.util.data.PixelImage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;

import java.util.HashMap;

public class NbtUtil {
    public static HashMap<BlockPos, PixelImage> readServerImageList(CompoundTag tag){
        HashMap<BlockPos, PixelImage> map = new HashMap<>();
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
                map.put(NbtUtils.readBlockPos(nbt.getCompound("blockpos")), new PixelImage(width, height, pixels));
            });
        }

        return map;
    }

    public static CompoundTag writeServerImageList(HashMap<BlockPos, PixelImage> images){
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();

        images.forEach((pos, image) -> {
            CompoundTag data = new CompoundTag();
            data.putInt("width", image.getWidth());
            data.putInt("height", image.getHeight());
            data.put("blockpos", NbtUtils.writeBlockPos(pos));
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
}
