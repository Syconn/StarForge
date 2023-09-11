package mod.stf.syconn.util.data;

import mod.stf.syconn.api.util.ChunkUtil;
import mod.stf.syconn.api.util.data.VectorData;
import mod.stf.syconn.init.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkInfo {

    private List<BlockInChunkData> blocks = new ArrayList<>();
    private final LevelChunk chunk;
    private final int x;
    private final int z;
    private final boolean posx, negx, posz, negz;

    public ChunkInfo(int x, int z, LevelChunk chunk, boolean posx, boolean negx, boolean posz, boolean negz) {
        this.x = x;
        this.z = z;
        this.posx = posx;
        this.negx = negx;
        this.posz = posz;
        this.negz = negz;
        this.chunk = chunk;
    }

    public ChunkInfo(CompoundTag tag, int lowestY) {
        this.x = tag.getInt("x");
        this.z = tag.getInt("z");
        this.posx = tag.getBoolean("posx");
        this.negx = tag.getBoolean("negx");
        this.posz = tag.getBoolean("posz");
        this.negz = tag.getBoolean("negz");
        this.chunk = Minecraft.getInstance().level.getChunk(tag.getInt("chunkx"), tag.getInt("chunkz"));
        this.blocks = BlockInChunkData.readAll(tag.getCompound("blocks"), chunk, posx, negx, posz, negz, lowestY);
    }

    public void createChunkRenderer(int lowestY, int renderY, Level level) {
        int highestY = ChunkUtil.getHighestBlock(chunk).getY();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = lowestY; y < highestY + 1; y++) {
                    BlockPos pos = new BlockPos(chunk.getPos().getBlockX(x), y, chunk.getPos().getBlockZ(z));
                    if (renderAble(pos, level))
                        blocks.add(new BlockInChunkData(chunk.getBlockState(pos), pos, new VectorData(chunk.getLevel(), pos, posx, negx, posz, negz, lowestY), x, z));
                    else if (pos.getY() >= renderY + 1 && !level.getBlockState(pos).is(ModTags.Blocks.DONT_RENDER_BLOCK) && !level.getBlockState(pos).is(Blocks.AIR)) {
                        for (Direction d : Direction.values()) {
                            if (VectorData.shouldRenderWithinChunk(level.getChunkAt(pos).getPos(), pos, d, posx, negx, posz, negz)) {
                                blocks.add(new BlockInChunkData(Blocks.STONE.defaultBlockState(), pos, new VectorData(chunk.getLevel(), pos, posx, negx, posz, negz, lowestY), x, z));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean renderAble(BlockPos pos, Level level) {
        if (!level.getBlockState(pos).is(ModTags.Blocks.DONT_RENDER_BLOCK) && !level.getBlockState(pos).is(Blocks.AIR)) {
            for (Direction d : Direction.values()) {
                BlockState state = level.getBlockState(pos.relative(d));
                if (!state.canOcclude() && state.getFluidState().isEmpty())
                    if (level.getBrightness(LightLayer.SKY, pos.relative(d)) > 4)
                        return true;
                if (!Block.isShapeFullBlock(state.getShape(level, pos.relative(d))) && d != Direction.UP && !state.getFluidState().isEmpty() && // WTF DOES THIS DO (IDR)
                        !(!state.getFluidState().isEmpty() && level.getBlockState(pos.relative(d).relative(Direction.UP)).getFluidState().isEmpty())) return true;
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

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", x);
        tag.putInt("z", z);
        tag.putBoolean("posx", posx);
        tag.putBoolean("posz", posz);
        tag.putBoolean("negz", negz);
        tag.putBoolean("negx", negx);
        tag.putInt("chunkx", chunk.getPos().x);
        tag.putInt("chunkz", chunk.getPos().z);
        tag.put("blocks", BlockInChunkData.saveAll(blocks));
        return tag;
    }

    public static CompoundTag saveAll(List<ChunkInfo> data) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        data.forEach(chunk -> listTag.add(chunk.save()));
        tag.put("chunks", listTag);
        return tag;
    }

    public static List<ChunkInfo> readAll(CompoundTag tag, int lowestY) {
        List<ChunkInfo> data = new ArrayList<>();
        if (tag.contains("chunks", Tag.TAG_LIST)) tag.getList("chunks", Tag.TAG_COMPOUND).forEach(chunk -> data.add(new ChunkInfo((CompoundTag) chunk, lowestY)));
        return data;
    }
}
