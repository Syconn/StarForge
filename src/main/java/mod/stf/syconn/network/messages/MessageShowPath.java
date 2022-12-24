package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageShowPath implements IMessage<MessageShowPath> {

    private boolean enabled;
    private BlockPos pos;

    public MessageShowPath() {}

    public MessageShowPath(boolean enabled, BlockPos pos) {
        this.enabled = enabled;
        this.pos = pos;
    }

    @Override
    public void encode(MessageShowPath message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.enabled);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageShowPath decode(FriendlyByteBuf buffer) {
        return new MessageShowPath(buffer.readBoolean(), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageShowPath message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null && player.level.getBlockEntity(message.pos) instanceof NavBE be){
                be.showPath(message.enabled);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
