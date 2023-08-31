package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.ChunkUtil;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.data.ChunkData;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class MapBe extends ClientBlockEntity {

    @OnlyIn(Dist.CLIENT)
    private final List<ChunkData> chunks = new ArrayList<>();
    @OnlyIn(Dist.CLIENT)
    private int lowestY = 0;

    public MapBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MAP_BE.get(), pWorldPosition, pBlockState);
        Level level = Minecraft.getInstance().level; // TODO MOVE TO PROPER PLACE WONT WORK WHEN LOADING WORLD
        ChunkPos chunkPos = level.getChunkAt(pWorldPosition).getPos();
        if (level.isClientSide()) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    LevelChunk chunk = level.getChunk(chunkPos.x + x, chunkPos.z + z);
                    chunks.add(new ChunkData(chunk.getPos().x - chunkPos.x, chunk.getPos().z - chunkPos.z, chunk));
                }
            }
        }
        lowestY = ChunkUtil.getLowestSurfaceBlock(chunks).getY();
    }

    @OnlyIn(Dist.CLIENT)
    public List<ChunkData> getChunk() {
        return chunks;
    }

    @OnlyIn(Dist.CLIENT)
    public int getLowestY() {
        return lowestY;
    }

    protected CompoundTag saveData() {
        CompoundTag tag = new CompoundTag();
        return tag;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
    }

    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }
}
