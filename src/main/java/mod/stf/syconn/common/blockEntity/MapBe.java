package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.Config;
import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.ChunkUtil;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.MultiBlockAlignment;
import mod.stf.syconn.util.data.ChunkData;
import net.minecraft.client.Minecraft;
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

    private List<ChunkData> chunks = new ArrayList<>();
    private int lowestY = 0;
    private ChunkPos centerPos = Minecraft.getInstance().level.getChunkAt(worldPosition).getPos(); // TODO WONT WORK ON WORLD LOAD NO LEVEL YET

    public MapBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MAP_BE.get(), pWorldPosition, pBlockState);
    }

    private void setChunk(Level level, ChunkPos center) { // TODO Chunk Save System with Update Checks (SIMILAR TO SKINDATA)
        chunks.clear();                                   // TODO FOR QUESTIONABLE LIMITS LIKE LOWEST Y MAKE IT
        int min = -1;                                     // TODO FACE ISSUES AGAIN - VOID AIR
        int max = 1;
        if (!level.isClientSide()) {
            for (int x = min; x <= max; x++) {
                for (int z = min; z <= max; z++) {
                    LevelChunk chunk = level.getChunk(center.x + x, center.z + z);
                    level.getServer().getLevel(Level.OVERWORLD).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
                    chunks.add(new ChunkData(chunk.getPos().x - center.x, chunk.getPos().z - center.z, chunk, x == max, x == min, z == max, z == min, lowestY));
                }
            }

            lowestY = ChunkUtil.getLowestSurfaceBlock(chunks).getY();
            centerPos = center;
            if (lowestY < Config.minYRenderHeight.get()) lowestY = Config.minYRenderHeight.get();
            for (ChunkData chunk : chunks) chunk.createChunkRenderer(lowestY, level);
        }
        update();
    }

    public List<ChunkData> getChunk() {
        return chunks;
    }

    public int getLowestY() {
        return lowestY;
    }

    protected CompoundTag saveData() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("lowesty", lowestY);
        tag.putInt("chunkx", centerPos.x);
        tag.putInt("chunkz", centerPos.z);
        if (!chunks.isEmpty()) tag.put("chunks", ChunkData.saveAll(chunks));
        return tag;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("lowesty", lowestY);
        pTag.putInt("chunkx", centerPos.x);
        pTag.putInt("chunkz", centerPos.z);
        if (!chunks.isEmpty()) pTag.put("chunks", ChunkData.saveAll(chunks));
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        lowestY = pTag.getInt("lowesty");
        centerPos = new ChunkPos(pTag.getInt("chunkx"), pTag.getInt("chunkz"));
        if (pTag.contains("chunks")) chunks = ChunkData.readAll(pTag.getCompound("chunks"), lowestY);
    }

    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }

    public void onClick(Level level, MultiBlockAlignment al) {
        setChunk(level, new ChunkPos(centerPos.x - al.getX(), centerPos.z - al.getZ()));
    }
}
