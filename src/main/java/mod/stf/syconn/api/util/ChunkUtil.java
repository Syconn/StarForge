package mod.stf.syconn.api.util;

import mod.stf.syconn.init.ModTags;
import mod.stf.syconn.util.data.ChunkInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtil {

    public static BlockPos getHighestBlock(Level level, ChunkPos chunkPos) {
        BlockPos pos = new BlockPos(chunkPos.getBlockX(0), level.getHeight(Heightmap.Types.WORLD_SURFACE,chunkPos.getBlockX(0), chunkPos.getBlockZ(0)), chunkPos.getBlockZ(0));
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, chunkPos.getBlockX(x), chunkPos.getBlockZ(z));
                if (y > pos.getY()) pos = new BlockPos(chunkPos.getBlockX(x), y, chunkPos.getBlockZ(z));
            }
        }
        return pos;
    }

    public static BlockPos getLowestSurfaceBlock(Level level, ChunkPos chunkPos) {
        BlockPos pos = new BlockPos(chunkPos.getBlockX(0), level.getHeight(Heightmap.Types.WORLD_SURFACE, chunkPos.getBlockX(0), chunkPos.getBlockZ(0)), chunkPos.getBlockZ(0));
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, chunkPos.getBlockX(x), chunkPos.getBlockZ(z));
                if (y < pos.getY()) pos = new BlockPos(chunkPos.getBlockX(x), y, chunkPos.getBlockZ(z));
            }
        }
        return pos;
    }

    public static BlockPos getLowestSurfaceBlock(Level level, List<ChunkInfo> chunks) {
        ChunkPos chunk = chunks.get(0).getChunkPos();
        BlockPos y = new BlockPos(chunk.getBlockX(0), level.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getBlockX(0), chunk.getBlockZ(0)), chunk.getBlockZ(0));
        for (ChunkInfo data : chunks) {
            if (y.getY() > getLowestSurfaceBlock(level, data.getChunkPos()).getY()) y = getLowestSurfaceBlock(level, data.getChunkPos());
        }
        return y;
    }

    public static List<Integer> getEdgeSurfaceBlock(LevelChunk chunk, boolean posx, boolean negx, boolean posz, boolean negz) {
        List<Integer> values = new ArrayList<>();
        if (negz) {
            for (int i = 0; i < 16; i++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(i), chunk.getPos().getBlockZ(0));
                if (!chunk.getBlockState(new BlockPos(chunk.getPos().getBlockX(i), y, chunk.getPos().getBlockZ(0))).is(ModTags.Blocks.NOT_GROUND_BLOCK)) values.add(y);
            }
        }
        if (posz) {
            for (int i = 0; i < 16; i++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(i), chunk.getPos().getBlockZ(15));
                if (!chunk.getBlockState(new BlockPos(chunk.getPos().getBlockX(i), y, chunk.getPos().getBlockZ(15))).is(ModTags.Blocks.NOT_GROUND_BLOCK)) values.add(y);
            }
        }

        if (negx) {
            for (int i = 0; i < 16; i++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(0), chunk.getPos().getBlockZ(i));
                if (!chunk.getBlockState(new BlockPos(chunk.getPos().getBlockX(0), y, chunk.getPos().getBlockZ(i))).is(ModTags.Blocks.NOT_GROUND_BLOCK)) values.add(y);
            }
        }

        if (posx) {
            for (int i = 0; i < 16; i++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(15), chunk.getPos().getBlockZ(i));
                if (!chunk.getBlockState(new BlockPos(chunk.getPos().getBlockX(15), y, chunk.getPos().getBlockZ(i))).is(ModTags.Blocks.NOT_GROUND_BLOCK)) values.add(y);
            }
        }

        return values;
    }
}
