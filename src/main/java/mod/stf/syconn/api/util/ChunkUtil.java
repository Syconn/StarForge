package mod.stf.syconn.api.util;

import mod.stf.syconn.init.ModTags;
import mod.stf.syconn.util.data.ChunkInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtil {

    public static BlockPos getHighestBlock(LevelChunk chunk) {
        BlockPos pos = new BlockPos(chunk.getPos().getBlockX(0), chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(0), chunk.getPos().getBlockZ(0)), chunk.getPos().getBlockZ(0));
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(x), chunk.getPos().getBlockZ(z));
                if (y > pos.getY()) pos = new BlockPos(chunk.getPos().getBlockX(x), y, chunk.getPos().getBlockZ(z));
            }
        }
        return pos;
    }

    public static BlockPos getLowestSurfaceBlock(LevelChunk chunk) {
        BlockPos pos = new BlockPos(chunk.getPos().getBlockX(0), chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(0), chunk.getPos().getBlockZ(0)), chunk.getPos().getBlockZ(0));
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getPos().getBlockX(x), chunk.getPos().getBlockZ(z));
                if (y < pos.getY()) pos = new BlockPos(chunk.getPos().getBlockX(x), y, chunk.getPos().getBlockZ(z));
            }
        }
        return pos;
    }

    public static BlockPos getLowestSurfaceBlock(List<ChunkInfo> chunks) {
        ChunkPos chunk = chunks.get(0).getChunk().getPos();
        BlockPos y = new BlockPos(chunk.getBlockX(0), chunks.get(0).getChunk().getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getBlockX(0), chunk.getBlockZ(0)), chunk.getBlockZ(0));
        for (ChunkInfo data : chunks) {
            if (y.getY() > getLowestSurfaceBlock(data.getChunk()).getY()) y = getLowestSurfaceBlock(data.getChunk());
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
