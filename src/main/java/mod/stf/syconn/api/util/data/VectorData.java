package mod.stf.syconn.api.util.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.HashMap;
import java.util.Map;

public class VectorData {

    private final Map<Direction, Boolean> values = new HashMap<>();
    private final BlockPos pos;
    private final Level level;

    public VectorData(Level level, BlockPos pos, LevelChunk chunk) { // TODO FIX SIDES
        for (Direction d : Direction.values()) {
            values.put(d, chunk != level.getChunkAt(pos.relative(d)) || level.getBlockState(pos.relative(d)).getFluidState().isEmpty() || Block.isShapeFullBlock(level.getBlockState(pos.relative(d)).getShape(level, pos)));
        }
        this.level = level;
        this.pos = pos;
    }

    public boolean getVectorData(Direction d) {
        return values.get(d);
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getLevel() {
        return level;
    }
}
