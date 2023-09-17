package mod.stf.syconn.world.data;

import com.google.common.primitives.Ints;
import mod.stf.syconn.Config;
import mod.stf.syconn.api.capability.ISSavable;
import mod.stf.syconn.api.util.ChunkUtil;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.util.data.ChunkInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.*;

public class ChunkData implements ISSavable {

    private final Map<UUID, List<BlockPos>> player_probes = new HashMap<>();
    private final Map<BlockPos, List<ChunkInfo>> renderer = new HashMap<>();

    public boolean addOrRemovePosition(UUID uuid, BlockPos pos) {
        if (player_probes.containsKey(uuid)){
            List<BlockPos> posList = player_probes.get(uuid);
            if (posList.contains(pos)) {
                posList.remove(pos);
                player_probes.put(uuid, posList);
                return false;
            } else {
                posList.add(pos);
                player_probes.put(uuid, posList);
            }
        } else player_probes.put(uuid, List.of(pos));
        return true;
    }

    public void remove(BlockPos pos) {
        player_probes.forEach((uuid, posList) -> {
            posList.remove(pos);
            player_probes.put(uuid, posList);
        });
    }

    public void changePos(BlockPos pos, BlockPos newPos) {
        player_probes.forEach((uuid, posList) -> {
            posList.remove(pos);
            posList.add(newPos);
            player_probes.put(uuid, posList);
        });
    }

    public void update(Level level) {
        renderer.clear();
        player_probes.forEach((uuid, posList) -> {
            posList.forEach(pos -> { if (!level.getBlockState(pos).is(ModBlocks.PROBE.get())) posList.remove(pos); });
            player_probes.put(uuid, posList);
//            posList.forEach(pos -> { if (!renderer.containsKey(pos)) renderer.put(pos, setChunk(level, level.getChunkAt(pos).getPos())); });
        });
    }

    private List<ChunkInfo> setChunk(Level level, ChunkPos center) {
        List<ChunkInfo> chunks = new ArrayList<>();
        List<Integer> sideYs = new ArrayList<>();
        int max = 2;
        for (int x = -max; x <= max; x++) {
            for (int z = -max; z <= max; z++) {
                LevelChunk chunk = level.getChunk(center.x + x, center.z + z);
//                level.getServer().getLevel(Level.OVERWORLD).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
                chunks.add(new ChunkInfo(chunk.getPos().x - center.x, chunk.getPos().z - center.z, chunk, x == max, x == -max, z == max, z == -max));
                sideYs.addAll(ChunkUtil.getEdgeSurfaceBlock(chunk, x == max, x == -max, z == max, z == -max));
            }
        }
        int lowestY = ChunkUtil.getLowestSurfaceBlock(chunks).getY();
        int renderY = Mths.mode(Ints.toArray(sideYs));
        if (lowestY < Config.minYRenderHeight.get()) lowestY = Config.minYRenderHeight.get();
        for (ChunkInfo chunk : chunks) chunk.createChunkRenderer(lowestY, renderY, level);
        return chunks;
    }

    public void saveNBTData(CompoundTag compound) {
        ListTag map = new ListTag();
        player_probes.forEach((uuid, list) -> {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("uuid", uuid);
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
        renderer.forEach((pos, info) -> {
            CompoundTag tag = new CompoundTag();
            tag.put("pos", NbtUtils.writeBlockPos(pos));
            ListTag infoList = new ListTag();
            info.forEach(chunk -> {
                CompoundTag tag2 = new CompoundTag();
                tag2.put("data", chunk.save());
                infoList.add(tag2);
            });
            tag.put("info", infoList);
            map.add(tag);
        });
        compound.put("renderer", map);
    }

    public void loadNBTData(CompoundTag compound) {
        this.player_probes.clear();
        this.renderer.clear();
        if (compound.contains("player_probes", Tag.TAG_LIST)){
            ListTag map = compound.getList("player_probes", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                List<BlockPos> posList = new ArrayList<>();
                ListTag posses = tag.getList("posses", Tag.TAG_COMPOUND);
                posses.forEach(tag1 -> posList.add(NbtUtils.readBlockPos(((CompoundTag) tag1).getCompound("pos"))));
                player_probes.put(tag.getUUID("uuid"), posList);
            });
        }
        if (compound.contains("renderer", Tag.TAG_LIST)){
            ListTag map = compound.getList("renderer", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                List<ChunkInfo> info = new ArrayList<>();
                ListTag infoList = tag.getList("info", Tag.TAG_COMPOUND);
                infoList.forEach(tag1 -> info.add(new ChunkInfo(((CompoundTag) tag1).getCompound("info"))));
                renderer.put(NbtUtils.readBlockPos(tag.getCompound("pos")), info);
            });
        }
    }
}
