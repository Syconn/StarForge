package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HoloBE extends BlockEntity {

    public HoloBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.HOLO_BE.get(), pWorldPosition, pBlockState);
    }

    public void tickServer() {

    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void getX(){

    }




    // Get an NBT compound to sync to the client with SPacketChunkData, used for initial loading of the chunk or when many blocks change at once. This compound comes back to you clientside in handleUpdateTag
    @Override
    public CompoundTag getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }
}
