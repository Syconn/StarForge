package mod.stf.syconn.api.util.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class Schematic {

    public static CompoundTag createSchematic(BlockPos pos1, BlockPos pos2) {
        CompoundTag tag = new CompoundTag();
        BoundingBox boundingbox = BoundingBox.fromCorners(pos1, pos2);
        tag.put("pos1", NbtUtils.writeBlockPos(pos1));
        tag.put("pos2", NbtUtils.writeBlockPos(pos2));
        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x){
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

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x){
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    if (!(level.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof AirBlock)){
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

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); ++x){
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); ++y) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); ++z) {
                    if (level.getBlockState(new BlockPos(x, y, z)).getBlock() == block){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
