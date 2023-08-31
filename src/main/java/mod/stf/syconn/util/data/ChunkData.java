package mod.stf.syconn.util.data;

import mod.stf.syconn.api.util.ChunkUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkData {

    private final List<BlockInChunkData> blocks = new ArrayList<>();
    private final LevelChunk chunk;
    private final int x;
    private final int z;

    public ChunkData(int x, int z, LevelChunk chunk) {
        this.x = x;
        this.z = z;
        this.chunk = chunk;
        createChunkRenderer(chunk);
    }

    private void createChunkRenderer(LevelChunk levelchunk) {
        int lowestY = ChunkUtil.getLowestSurfaceBlock(levelchunk).getY();
        int highestY = ChunkUtil.getHighestBlock(levelchunk).getY();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = lowestY; y < highestY + 1; y++) {
                    BlockPos pos = new BlockPos(levelchunk.getPos().getBlockX(x), y, levelchunk.getPos().getBlockZ(z));
                    if (renderAble(pos, Minecraft.getInstance().level)) {
                        blocks.add(new BlockInChunkData(levelchunk.getBlockState(pos), pos, x, z));
                    }
                }
            }
        }
    }

    private boolean renderAble(BlockPos pos, Level level) { // TODO OPTMISE SIDE SIMILAR TO WATER
        for (Direction d : Direction.values()) {
            BlockState state = level.getBlockState(pos.relative(d));
            if (state.getBlock() instanceof CropBlock) return true;
            if (state.getBlock() instanceof DoublePlantBlock) return true;
            if (chunk != level.getChunkAt(pos.relative(d))) return true;
            if (!Block.isShapeFullBlock(state.getShape(level, pos)) && state.getFluidState().isEmpty()) {
                if (!(state.getBlock() instanceof FarmBlock) && !(state.getBlock() instanceof DirtPathBlock)) return true;
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public LevelChunk getChunk() {
        return chunk;
    }

    public List<BlockInChunkData> getBlocks() {
        return blocks;
    }
}
