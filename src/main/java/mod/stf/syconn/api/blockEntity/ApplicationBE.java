package mod.stf.syconn.api.blockEntity;

import mod.stf.syconn.api.containers.ApplicationContainer;
import mod.stf.syconn.api.util.applications.BasicApplication;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ApplicationBE<T extends ApplicationContainer> extends ClientMenuBlockEntity {

    protected BasicApplication<T> application;

    public ApplicationBE(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, BasicApplication<T> application) {
        super(pType, pWorldPosition, pBlockState);
        this.application = application;
    }

    public BasicApplication<T> getApplication() {
        return application;
    }
}
