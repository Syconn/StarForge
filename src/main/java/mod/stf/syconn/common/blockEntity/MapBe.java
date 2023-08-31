package mod.stf.syconn.common.blockEntity;

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

    @OnlyIn(Dist.CLIENT)
    private ChunkPos centerPos;

    public MapBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MAP_BE.get(), pWorldPosition, pBlockState);
        setChunk(Minecraft.getInstance().level.getChunkAt(pWorldPosition).getPos()); // TODO MOVE TO PROPER PLACE WONT WORK WHEN LOADING WORLD
    }

    private void setChunk(ChunkPos center) {
        chunks.clear();
        Level level = Minecraft.getInstance().level;
        if (level.isClientSide()) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    LevelChunk chunk = level.getChunk(center.x + x, center.z + z);
                    //level.getServer().getLevel(Level.OVERWORLD).setChunkForced(chunk.getPos().x, chunk.getPos().z, true); // TODO MUST DO SERVER SIDE INTEGRATION NEXT
                    chunks.add(new ChunkData(chunk.getPos().x - center.x, chunk.getPos().z - center.z, chunk));
                }
            }
        }
        lowestY = ChunkUtil.getLowestSurfaceBlock(chunks).getY();
        centerPos = center;
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

    public void onClick(MultiBlockAlignment al) {
        setChunk(new ChunkPos(centerPos.x - al.getX(), centerPos.z - al.getZ()));
    }
}
