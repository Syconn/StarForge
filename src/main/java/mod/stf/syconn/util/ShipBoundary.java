package mod.stf.syconn.util;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ShipBoundary {

    private final List<BlockPos> ship;
    private MutablePos pos1;
    private MutablePos pos2;

    public ShipBoundary(List<BlockPos> ship) {
        this.ship = ship;
        findBoarders();
    }

    public ShipBoundary(List<BlockPos> ship, MutablePos pos1, MutablePos pos2) {
        this.ship = ship;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public BlockPos getPos1() {
        return pos1.getPos();
    }

    public BlockPos getPos2() {
        return pos2.getPos();
    }

    public void rotate(Direction i, Direction t){
        BlockPos c = getCenter();
        pos1.rotate(c, i, t);
        pos2.rotate(c, i, t);
    }

    public void move(int x, int y, int z){
        pos1.move(x, y, z);
        pos2.move(x, y, z);
    }

    public BlockPos getCenter(){
        return Mths.getCenter(pos1.getPos(), pos2.getPos());
    }

    public boolean collision(Level l){
        for (BlockPos pos : getBoundary()){
            BlockState bs = l.getBlockState(pos);
            if (!bs.is(ModTags.Blocks.OPEN_BLOCK)){
                boolean match = false;
                for (BlockPos pos3 : ship)
                    if (pos3.equals(pos)){
                        match = true;
                }
                if (!match)
                    return true;
            }
        }
        return false;
    }

    public boolean testCollision(Level l, int x, int y, int z){
        for (BlockPos pos : getBoundary(x, y, z)){
            BlockState bs = l.getBlockState(pos);
            if (!bs.is(ModTags.Blocks.OPEN_BLOCK)){
                boolean match = false;
                for (BlockPos pos3 : ship)
                    if (pos3.equals(pos)){
                        match = true;
                    }
                if (!match)
                    return true;
            }
        }
        return false;
    }

    public Iterable<BlockPos> getBoundary(){
        return BlockPos.betweenClosed(pos1.getPos(), pos2.getPos());
    }

    public Iterable<BlockPos> getBoundary(int x, int y, int z){
        return BlockPos.betweenClosed(pos1.of(x, y, z).getPos(), pos2.of(x, y, z).getPos());
    }

    private void findBoarders(){
        int xMax = ship.get(0).getX(), xMin = ship.get(0).getX(), yMin = ship.get(0).getY(), yMax = ship.get(0).getY(), zMax = ship.get(0).getZ(), zMin = ship.get(0).getZ();
        for (BlockPos pos : ship){
            if (xMin > pos.getX())
                xMin = pos.getX();
            if (xMax < pos.getX())
                xMax = pos.getX();
            if (yMin > pos.getY())
                yMin = pos.getY();
            if (yMax < pos.getY())
                yMax = pos.getY();
            if (zMin > pos.getZ())
                zMin = pos.getZ();
            if (zMax < pos.getZ())
                zMax = pos.getZ();
        }
        pos1 = new MutablePos(xMin, yMin, zMin);
        pos2 = new MutablePos(xMax, yMax, zMax);
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("blocks", NbtUtil.writeBlockPositions(this.ship));
        tag.put("pos1", pos1.save());
        tag.put("pos2", pos2.save());
        return tag;
    }

    public static ShipBoundary read(CompoundTag tag){
        return new ShipBoundary(NbtUtil.readBlockPositions(tag.getCompound("blocks")), MutablePos.read(tag.getCompound("pos1")), MutablePos.read(tag.getCompound("pos2")));
    }

    static class MutablePos {
        private int x;
        private int y;
        private int z;

        public MutablePos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public void move(int x, int y, int z){
            this.x += x;
            this.y += y;
            this.z += z;
        }

        public void set(BlockPos pos){
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }

        public BlockPos getPos(){
            return new BlockPos(x, y, z);
        }

        public void rotate(BlockPos rp, Direction i, Direction t){
            BlockPos pos = RotationHelper.rotate(rp, getPos(), i, t);
            set(pos);
        }

        public MutablePos of(int x, int y, int z){
            return new MutablePos(this.x + x, this.y + y, this.z + z);
        }

        public CompoundTag save(){
            CompoundTag tag = new CompoundTag();
            tag.putInt("x", this.x);
            tag.putInt("y", this.y);
            tag.putInt("z", this.z);
            return tag;
        }

        public static MutablePos read(CompoundTag tag){
            return new MutablePos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
        }
    }
}
