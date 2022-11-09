package mod.stf.syconn.api.util.data;

import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class Schematic {

    private List<BlockID> blockIDs = new ArrayList<>();

    public Schematic(List<BlockID> blocks) {
        this.blockIDs = blocks;
    }

    public Schematic(List<BlockPos> blocks, int i) {
        this.blockIDs = genStates(blocks);
    }

    public Schematic(List<BlockPos> blocks, List<BlockState> states) {
        for (int i = 0; i < blocks.size(); i++){
            blockIDs.add(new BlockID(states.get(i), blocks.get(i)));
        }
    }

    public List<BlockID> getBlockIDs() {
        return blockIDs;
    }

    public AnchorPos getAnchor(){
        BlockPos pos = blockIDs.get(0).pos();
        double bX = pos.getX(), bY = pos.getY(), bZ = pos.getZ(), sX = pos.getX(), sY = pos.getY(), sZ = pos.getZ();
        for (BlockID id : blockIDs) {
            BlockPos pos2 = id.pos();
            if (pos2.getX() > bX) bX = pos2.getX();
            if (pos2.getY() > bY) bY = pos2.getY();
            if (pos2.getZ() > bZ) bZ = pos2.getZ();
            if (pos2.getX() < sX) sX = pos2.getX();
            if (pos2.getY() < sY) sY = pos2.getY();
            if (pos2.getZ() < sZ) sZ = pos2.getZ();
        }
        return new AnchorPos((int) ((bX + sX) / 2), (int) sY, (int) ((bZ + sZ) / 2));
    }

    public List<BlockID> genStates(List<BlockPos> positions){
        List<BlockID> bs = new ArrayList<>();
        for (BlockPos pos : positions) {
            bs.add(new BlockID(Minecraft.getInstance().level.getBlockState(pos), pos));
        }
        return bs;
    }

    public CompoundTag saveSchematic() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("size", blockIDs.size());
        for (int i = 0; i < blockIDs.size(); i++){
            tag.put(String.valueOf(i), blockIDs.get(i).write());
        }
        return tag;
    }

    public static Schematic readSchematic(CompoundTag tag) {
        List<BlockID> posses = new ArrayList<>();
        for (int i = 0; i < tag.getInt("size"); i++){
            posses.add(BlockID.read(tag.getCompound(String.valueOf(i))));
        }
        return new Schematic(posses);
    }

    //REMOVES AIR BLOCKS
    public Schematic cleanSchematic(){
        List<BlockID> ids = new ArrayList<>();
        for (BlockID id : blockIDs){
            Block b = id.state().getBlock();
            if (b == Blocks.AIR || b == Blocks.CAVE_AIR || b == Blocks.VOID_AIR){
                ids.add(id);
            }
        }
        blockIDs.removeAll(ids);
        return this;
    }

    public boolean containsBlock(Block block){
        for (BlockID id : getBlockIDs()){
            if (id.state().getBlock() == block){
                return true;
            }
        }
        return false;
    }

    public boolean containsBlockPos(BlockPos blockPos){
        for (BlockID id : getBlockIDs()){
            if (id.pos() == blockPos){
                return true;
            }
        }
        return false;
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
        return new Schematic(posses, 0);
    }
}
