package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;

public class ProbeBe extends ClientBlockEntity implements Nameable {

    private Component name = Component.literal(new ChunkPos(worldPosition).toString());

    public ProbeBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PROBE_BE.get(), pWorldPosition, pBlockState);
    }

    public Component getName() {
        return this.name;
    }

    public Component getCustomName() {
        return this.name;
    }

    public void setCustomName(Component pName) {
        this.name = pName;
        update();
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("name", this.name.getString());
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.name = Component.literal(pTag.getString("name"));
    }
}
