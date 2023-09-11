package mod.stf.syconn.world.data;

import mod.stf.syconn.api.capability.ISSavable;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.util.data.ChunkInfo;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class ChunkData implements ISSavable {

    Map<ChunkPos, ChunkInfo> chunks = new HashMap<>();

    public ChunkInfo getOrCreateChunk(ChunkPos chunkPos, Level level) {
//        return chunks.get(chunkPos);
    }

    public void saveNBTData(CompoundTag compound) {
        ListTag map = new ListTag();
        chunks.forEach((pos, info) -> {
            CompoundTag tag = new CompoundTag();
            tag.putInt("chunkx", pos.x);
            tag.putInt("chunkz", pos.z);
            tag.put("chunk", info.save());
            map.add(tag);
        });
        compound.put("chunks", map);
    }

    public void loadNBTData(CompoundTag compound) {
        this.chunks.clear();
        if (compound.contains("chunks", Tag.TAG_LIST)){
            ListTag map = compound.getList("chunks", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                chunks.put(new ChunkPos(tag.getInt("chunkx"), tag.getInt("chunkz")), ChunkInfo.readAll());
            });
        }
    }
}
