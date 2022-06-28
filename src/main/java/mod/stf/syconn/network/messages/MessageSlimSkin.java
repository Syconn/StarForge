package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSlimSkin implements IMessage<MessageSlimSkin> {

    private boolean slim;
    private BlockPos pos;

    public MessageSlimSkin() {
    }

    public MessageSlimSkin(boolean slim, BlockPos pos) {
        this.slim = slim;
        this.pos = pos;
    }

    @Override
    public void encode(MessageSlimSkin message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.slim);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageSlimSkin decode(FriendlyByteBuf buffer) {
        return new MessageSlimSkin(buffer.readBoolean(), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageSlimSkin message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null && player.level.getBlockEntity(message.pos) instanceof HoloBE) {
                HoloBE be = (HoloBE) player.level.getBlockEntity(message.pos);
                be.setSlim(message.slim);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
