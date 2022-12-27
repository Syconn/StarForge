package mod.stf.syconn.util;

import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

public class MutablePos {

    private int x;
    private int y;
    private int z;

    public MutablePos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MutablePos(BlockPos pos){
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
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

    public void rotate(BlockPos rp, Schematic s, Direction i, Direction t){
        BlockPos pos = RotationHelper.rotate(rp, getPos(), new int[] {s.evenXLen(), s.evenZLen()}, i, t);
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

    @Override
    public String toString() {
        return "MutablePos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    static enum Way {
        X(1, 0, 0),
        Y(0, 1, 0),
        Z(0, 0, 1);

        private int x;
        private int y;
        private int z;

        Way(int x, int y, int z) {
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
    }
}
