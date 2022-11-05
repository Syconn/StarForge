package mod.stf.syconn.api.util.data;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class Schematic {

    private final List<BlockState> states;
    private final List<BlockPos> blocks;

    public Schematic(List<BlockPos> blocks) {
        this.blocks = blocks;
        this.states = genStates();
    }

    public List<BlockPos> getBlocks() {
        return blocks;
    }

    public List<BlockState> getStates() {
        return states;
    }

    public List<BlockState> genStates(){
        List<BlockState> bs = new ArrayList<>();
        for (BlockPos pos : getBlocks()) {
            bs.add(Minecraft.getInstance().level.getBlockState(pos));
        }
        return bs;
    }

    public CompoundTag saveSchematic() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("size", blocks.size());
        for (int i = 0; i < blocks.size(); i++){
            tag.put(String.valueOf(i), NbtUtils.writeBlockPos(blocks.get(i)));
        }
        return tag;
    }

    public static Schematic readSchematic(CompoundTag tag) {
        List<BlockPos> posses = new ArrayList<>();
        for (int i = 0; i < tag.getInt("size"); i++){
            posses.add(NbtUtils.readBlockPos(tag.getCompound(String.valueOf(i))));
        }
        return new Schematic(posses);
    }

    //REMOVES AIR BLOCKS
    public Schematic cleanSchematic(Level level){
        List<BlockPos> blocks2 = blocks;
        for (BlockPos pos2 : blocks2){
            Block b = level.getBlockState(pos2).getBlock();
            if (b == Blocks.AIR || b == Blocks.CAVE_AIR || b == Blocks.VOID_AIR){
                blocks.remove(pos2);
            }
        }
        return new Schematic(blocks2);
    }

    public boolean containsBlock(Block block, Level level){
        for (BlockPos pos : getBlocks()){
            if (level.getBlockState(pos).getBlock() == block){
                return true;
            }
        }
        return false;
    }

    public boolean containsBlockPos(BlockPos blockPos, Level level){
        for (BlockPos pos : getBlocks()){
            if (pos == blockPos){
                return true;
            }
        }
        return false;
    }

    public static Schematic fromSchematic(Level level, CompoundTag tag) {
        BlockPos pos1 = NbtUtils.readBlockPos(tag.getCompound("pos1"));
        BlockPos pos2 = NbtUtils.readBlockPos(tag.getCompound("pos2"));
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);
        List<BlockPos> posses = new ArrayList<>();

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    if (!(level.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof AirBlock)) {
                        posses.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return new Schematic(posses);
    }

    public static Schematic genSchematic(BlockPos pos1, BlockPos pos2) {
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);
        List<BlockPos> posses = new ArrayList<>();
        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    posses.add(new BlockPos(x, y, z));
                }
            }
        }
        return new Schematic(posses);
    }

    public static CompoundTag createSchematic(BlockPos pos1, BlockPos pos2) {
        CompoundTag tag = new CompoundTag();
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);
        tag.put("pos1", NbtUtils.writeBlockPos(pos1));
        tag.put("pos2", NbtUtils.writeBlockPos(pos2));
        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    tag.put("x" + x + "y" + y + "z" + z, NbtUtils.writeBlockPos(new BlockPos(x, y, z)));
                }
            }
        }
        return tag;
    }

    public static List<BlockPos> readSchematic(Level level, CompoundTag tag) {
        BlockPos pos1 = NbtUtils.readBlockPos(tag.getCompound("pos1"));
        BlockPos pos2 = NbtUtils.readBlockPos(tag.getCompound("pos2"));
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);
        List<BlockPos> posses = new ArrayList<>();

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    if (!(level.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof AirBlock)) {
                        posses.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return posses;
    }

    public static boolean containsBlock(Level level, CompoundTag tag, Block block) {
        BlockPos pos1 = NbtUtils.readBlockPos(tag.getCompound("pos1"));
        BlockPos pos2 = NbtUtils.readBlockPos(tag.getCompound("pos2"));
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    if (level.getBlockState(new BlockPos(x, y, z)).getBlock() == block) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
