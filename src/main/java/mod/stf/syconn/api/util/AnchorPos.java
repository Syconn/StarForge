package mod.stf.syconn.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public record AnchorPos(double x, double y, double z){

    public BlockPos getBlockPos(){
        return new BlockPos(x, y, z);
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.putDouble("x", x);
        tag.putDouble("y", y);
        tag.putDouble("z", z);
        return tag;
    }

    public static AnchorPos read(CompoundTag tag){
        return new AnchorPos(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
    }
}
