package mod.stf.syconn.api.util;

import mod.stf.syconn.util.data.ChunkData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

    public static BlockPos getLowestSurfaceBlock(List<ChunkData> chunks) {
        ChunkPos chunk = chunks.get(0).getChunk().getPos();
        BlockPos y = new BlockPos(chunk.getBlockX(0), chunks.get(0).getChunk().getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getBlockX(0), chunk.getBlockZ(0)), chunk.getBlockZ(0));
        for (ChunkData data : chunks) {
            System.out.println(getLowestSurfaceBlock(data.getChunk()).getY());
            if (y.getY() > getLowestSurfaceBlock(data.getChunk()).getY()) y = getLowestSurfaceBlock(data.getChunk());
        }
        return y;
//        List<Integer> heights = new ArrayList<>();
//        for (ChunkData data : chunks) heights.add(getLowestSurfaceBlock(data.getChunk()).getY());
//        Collections.sort(heights, Collections.reverseOrder());
//        if (heights.get(1) - heights.get(0) >= 10) heights.remove(0);
//        if (heights.get(1) - heights.get(0) >= 10) heights.remove(0);
//        return new BlockPos(0, (int) heights.stream().mapToDouble(a -> a).average().getAsDouble(), 0);
    }
}
