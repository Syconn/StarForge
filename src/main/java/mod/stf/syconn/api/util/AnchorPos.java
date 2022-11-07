package mod.stf.syconn.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public record AnchorPos(int x, int y, int z){

    public BlockPos getBlockPos(){
        return new BlockPos(x, y, z);
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", x);
        tag.putInt("y", y);
        tag.putInt("z", z);
        return tag;
    }

    public static AnchorPos read(CompoundTag tag){
        return new AnchorPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    }
}
