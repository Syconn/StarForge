package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageHoloMode implements IMessage<MessageHoloMode> {

    private String mode;
    private BlockPos pos;

    public MessageHoloMode() {}

    public MessageHoloMode(String mode, BlockPos pos) {
        this.mode = mode;
        this.pos = pos;
    }

    @Override
    public void encode(MessageHoloMode message, FriendlyByteBuf buffer) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("mode", message.mode);
        buffer.writeNbt(nbt);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageHoloMode decode(FriendlyByteBuf buffer) {
        CompoundTag nbt = buffer.readNbt();
        return new MessageHoloMode(nbt.getString("mode"), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageHoloMode message, Supplier<NetworkEvent.Context> supplier) {
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
