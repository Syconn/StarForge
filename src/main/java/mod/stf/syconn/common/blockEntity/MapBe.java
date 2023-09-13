package mod.stf.syconn.common.blockEntity;

import com.google.common.primitives.Ints;
import mod.stf.syconn.Config;
import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.ChunkUtil;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.MultiBlockAlignment;
import mod.stf.syconn.util.data.ChunkInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class MapBe extends ClientBlockEntity {

    private List<ChunkInfo> chunks = new ArrayList<>();
    private int lowestY = 0;
    private int renderY = 0;
    private ChunkPos centerPos = null;

    public MapBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MAP_BE.get(), pWorldPosition, pBlockState);
    }

    private void setChunk(Level level, ChunkPos center) {
        chunks.clear();
        int max = 2;
        if (!level.isClientSide()) {
            List<Integer> sideYs = new ArrayList<>();
            for (int x = -max; x <= max; x++) {
                for (int z = -max; z <= max; z++) {
                    LevelChunk chunk = level.getChunk(center.x + x, center.z + z);
//                    level.getServer().getLevel(Level.OVERWORLD).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
                    chunks.add(new ChunkInfo(chunk.getPos().x - center.x, chunk.getPos().z - center.z, chunk, x == max, x == -max, z == max, z == -max));
                    sideYs.addAll(ChunkUtil.getEdgeSurfaceBlock(chunk, x == max, x == -max, z == max, z == -max));
                }
            }
            lowestY = ChunkUtil.getLowestSurfaceBlock(chunks).getY();
            centerPos = center;
            renderY = Mths.mode(Ints.toArray(sideYs));
            if (lowestY < Config.minYRenderHeight.get()) lowestY = Config.minYRenderHeight.get();
            for (ChunkInfo chunk : chunks) chunk.createChunkRenderer(lowestY, renderY, level);
        }
        update();
    }

    public List<ChunkInfo> getChunk() {
        return chunks;
    }
    public int getRenderY() {
        return renderY;
    }

    protected CompoundTag saveData() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("lowesty", lowestY);
        tag.putInt("rendery", renderY);
        if (centerPos != null) {
            tag.putInt("chunkx", centerPos.x);
            tag.putInt("chunkz", centerPos.z);
        }
        if (!chunks.isEmpty()) tag.put("chunks", ChunkInfo.saveAll(chunks));
        return tag;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("lowesty", lowestY);
        pTag.putInt("rendery", renderY);
        if (centerPos != null) {
            pTag.putInt("chunkx", centerPos.x);
            pTag.putInt("chunkz", centerPos.z);
        }
        if (!chunks.isEmpty()) pTag.put("chunks", ChunkInfo.saveAll(chunks));
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        lowestY = pTag.getInt("lowesty");
        renderY = pTag.getInt("rendery");
        if (pTag.contains("chunkx")) centerPos = new ChunkPos(pTag.getInt("chunkx"), pTag.getInt("chunkz"));
        if (pTag.contains("chunks")) chunks = ChunkInfo.readAll(pTag.getCompound("chunks"));
    }

    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }

    public void onClick(Level level, BlockPos pos, MultiBlockAlignment al) {
        if (centerPos == null) centerPos = level.getChunkAt(pos).getPos();
        setChunk(level, new ChunkPos(centerPos.x - al.getX(), centerPos.z - al.getZ()));
    }
}
