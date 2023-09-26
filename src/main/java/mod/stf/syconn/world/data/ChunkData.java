package mod.stf.syconn.world.data;

import mod.stf.syconn.api.capability.ISSavable;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.util.data.ChunkHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkData implements ISSavable {


    // BLOCKPOS of Projectors | satalite can only connect to one projector | Projectors can have multiple Satalites
    private final Map<BlockPos, List<BlockPos>> projector_chunks = new HashMap<>();
    private final Map<ChunkPos, ChunkHandler> renderer = new HashMap<>(); // Hold Render Data for ChunkPos

    public ChunkHandler getChunkHandler(BlockPos pos, Level level) {
        if (!renderer.containsKey(new ChunkPos(pos))) renderer.put(new ChunkPos(pos), new ChunkHandler(level, new ChunkPos(pos)));
        return renderer.get(new ChunkPos(pos));
    }

    public List<BlockPos> getChunkOptions(BlockPos pos) {
        if (!projector_chunks.containsKey(pos)) return List.of();
        return projector_chunks.get(pos);
    }

    public void changeProbeLocation(BlockPos last, BlockPos next) {
        projector_chunks.forEach((proj, list) -> { if (list.contains(last)) { list.remove(last); list.add(next); }});
    }

    public void removeProbe(BlockPos pos) {
        projector_chunks.forEach((proj, list) -> list.remove(pos));
        renderer.remove(new ChunkPos(pos));
    }

    public void addProjectorOptions(BlockPos key, List<BlockPos> pos) {
        if (!pos.isEmpty()) {
            if (projector_chunks.containsKey(key)) {
                List<BlockPos> list = projector_chunks.get(key);
                list.addAll(pos);
                projector_chunks.put(key, list);
            } else projector_chunks.put(key, pos);
        }
    }

    public void removeProjector(BlockPos pos) {
        List<BlockPos> list = projector_chunks.remove(pos);
        if (list != null) list.forEach(pos2 -> renderer.remove(new ChunkPos(pos2)));
    }

    public void update(Level level) { // OPTIMISE MAYBE
        renderer.clear();
        projector_chunks.forEach((projector, posList) -> {
            posList.forEach(pos -> { if (!level.getBlockState(pos).is(ModBlocks.PROBE.get())) posList.remove(pos); });
            projector_chunks.put(projector, posList);
            posList.forEach(pos -> renderer.put(new ChunkPos(pos), new ChunkHandler(level, level.getChunkAt(pos).getPos())));
        });
    }

    public void saveNBTData(CompoundTag compound) {
        ListTag map = new ListTag();
        projector_chunks.forEach((projector, list) -> {
            CompoundTag tag = new CompoundTag();
            tag.put("projector", NbtUtils.writeBlockPos(projector));
            ListTag posses = new ListTag();
            list.forEach(pos -> {
                CompoundTag tag2 = new CompoundTag();
                tag2.put("pos", NbtUtils.writeBlockPos(pos));
                posses.add(tag2);
            });
            tag.put("posses", posses);
            map.add(tag);
        });
        compound.put("player_probes", map);
        compound.put("renderer", map);
        ListTag map2 = new ListTag();
        renderer.forEach((pos, info) -> {
            CompoundTag tag = new CompoundTag();
            tag.putInt("posx", pos.x);
            tag.putInt("posz", pos.z);
            tag.put("chunkhandler", info.save());
            map2.add(tag);
        });
        compound.put("renderer", map2);
    }

    public void loadNBTData(CompoundTag compound) {
        this.projector_chunks.clear();
        this.renderer.clear();
        if (compound.contains("player_probes", Tag.TAG_LIST)){
            ListTag map = compound.getList("player_probes", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                List<BlockPos> posList = new ArrayList<>();
                ListTag posses = tag.getList("posses", Tag.TAG_COMPOUND);
                posses.forEach(tag1 -> posList.add(NbtUtils.readBlockPos(((CompoundTag) tag1).getCompound("pos"))));
                projector_chunks.put(NbtUtils.readBlockPos(tag.getCompound("projector")), posList);
            });
        }
        if (compound.contains("renderer", Tag.TAG_LIST)){
            ListTag map = compound.getList("renderer", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                renderer.put(new ChunkPos(tag.getInt("posx"), tag.getInt("posz")), new ChunkHandler(tag.getCompound("chunkhandler")));
            });
        }
    }
}
