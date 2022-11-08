package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageNavEnabled implements IMessage<MessageNavEnabled> {

    private boolean enabled;
    private BlockPos pos;

    public MessageNavEnabled() {}

    public MessageNavEnabled(boolean enabled, BlockPos pos) {
        this.enabled = enabled;
        this.pos = pos;
    }

    @Override
    public void encode(MessageNavEnabled message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.enabled);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageNavEnabled decode(FriendlyByteBuf buffer) {
        return new MessageNavEnabled(buffer.readBoolean(), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageNavEnabled message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null && player.level.getBlockEntity(message.pos) instanceof NavBE be){
                be.setEnabled(message.enabled);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
