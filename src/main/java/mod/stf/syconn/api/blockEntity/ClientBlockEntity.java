package mod.stf.syconn.api.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class ClientBlockEntity extends BlockEntity {

    public ClientBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    protected abstract CompoundTag saveData();

    public CompoundTag getUpdateTag() {
        return saveData();
    }

    public void update(){
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 2);
        setChanged();
    }

    public static void update(Level level, BlockPos pos, BlockState state){
        level.sendBlockUpdated(pos, state, state, 2);
        setChanged(level, pos, state);
    }
}
