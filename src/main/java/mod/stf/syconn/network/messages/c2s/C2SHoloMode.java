package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SHoloMode implements IMessage<C2SHoloMode> {

    private String mode;
    private BlockPos pos;

    public C2SHoloMode() {}

    public C2SHoloMode(String mode, BlockPos pos) {
        this.mode = mode;
        this.pos = pos;
    }

    @Override
    public void encode(C2SHoloMode message, FriendlyByteBuf buffer) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("mode", message.mode);
        buffer.writeNbt(nbt);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public C2SHoloMode decode(FriendlyByteBuf buffer) {
        CompoundTag nbt = buffer.readNbt();
        return new C2SHoloMode(nbt.getString("mode"), buffer.readBlockPos());
    }

    @Override
    public void handle(C2SHoloMode message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null && player.level.getBlockEntity(message.pos) instanceof HoloBE) {
                HoloBE be = (HoloBE) player.level.getBlockEntity(message.pos);
                be.setMode(message.mode);
                be.setUrlOrName(message.mode);
                be.setSkin(null);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
