package mod.stf.syconn.api.util.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VectorData {

    private final Map<Direction, Boolean> fluidValues = new HashMap<>();
    private final List<Direction> blockFaces = new ArrayList<>();
    private final BlockPos pos;
    private final Level level;
    private final float r, g, b;
    private final float blockHeight;
    public VectorData(Level level, BlockPos pos, boolean posx, boolean negx, boolean posz, boolean negz) {
        this.level = level;
        this.pos = pos;
        for (Direction d : Direction.values()) { // TODO UNDERGROUND WATER
            // FLUIDS
            if (!level.getBlockState(pos).getFluidState().isEmpty()) fluidValues.put(d, shouldRenderWithinChunk(level.getChunkAt(pos).getPos(), pos, d, posx, negx, posz, negz)
                        || (!Block.isShapeFullBlock(level.getBlockState(pos.relative(d)).getShape(level, pos.relative(d))) && level.getBlockState(pos.relative(d)).getFluidState().isEmpty()) ||
                        (!level.getBlockState(pos.relative(d)).canOcclude() && level.getBlockState(pos.relative(d)).getFluidState().isEmpty()));
            // BLOCKS
            else if (shouldRenderWithinChunk(level.getChunkAt(pos).getPos(), pos, d, posx, negx, posz, negz) ||
                    (!Block.isShapeFullBlock(level.getBlockState(pos.relative(d)).getShape(level, pos.relative(d)))) ||
                    shouldRenderGlassFace(level, pos, d)) blockFaces.add(d);
        }
        blockHeight = level.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof AirBlock ? 0.8F : 1.0F;
        fluidValues.put(Direction.DOWN, false);
        FluidState fluid = level.getBlockState(pos).getFluidState();
        int color = IClientFluidTypeExtensions.of(fluid).getTintColor(fluid, level, pos);
        this.r = (color >> 16 & 0xFF) / 255.0F;
        this.g = (color >> 8 & 0xFF) / 255.0F;
        this.b = (color & 0xFF) / 255.0F;
    }

    public static boolean shouldRenderWithinChunk(ChunkPos block, BlockPos pos, Direction d, boolean posx, boolean negx, boolean posz, boolean negz) {
        if (negz && d == Direction.NORTH && block.getBlockZ(0) == pos.getZ()) return true;
        if (posz && d == Direction.SOUTH && block.getBlockZ(15) == pos.getZ()) return true;
        if (negx && d == Direction.WEST && block.getBlockX(0) == pos.getX()) return true;
        return posx && d == Direction.EAST && block.getBlockX(15) == pos.getX();
    }

    // REMOVES RENDERING OF Blocks Surrounded by underwater water - TODO might move to add Block Function
    public boolean isWaterPocket(BlockPos pos, Direction d) {
        return false;
    }

    public boolean shouldRenderGlassFace(Level level, BlockPos pos, Direction d) {
        if (level.getBlockState(pos).getBlock() instanceof HalfTransparentBlock) {
            if (level.getBlockState(pos.relative(d)).getBlock() instanceof HalfTransparentBlock) return false;
        }
        return !level.getBlockState(pos.relative(d)).canOcclude();
    }

    public float getBlockHeight() {
        return blockHeight;
    }

    public boolean getVectorData(Direction d) {
        if (fluidValues.get(d) == null) return false;
        return fluidValues.get(d);
    }

    public List<Direction> getBlockFaces() {
        return blockFaces;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getLevel() {
        return level;
    }

    public float[] getRGB() {
        return new float[] {r, g, b};
    }
}
