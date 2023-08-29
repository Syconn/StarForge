package mod.stf.syconn.network.messages.s2c;

import mod.stf.syconn.network.messages.IMessage;
import mod.stf.syconn.world.data.SkinData;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageResetHolo implements IMessage<MessageResetHolo> {

    private BlockPos pos;

    public MessageResetHolo() {
    }

    public MessageResetHolo(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encode(MessageResetHolo message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageResetHolo decode(FriendlyByteBuf buffer) {
        return new MessageResetHolo(buffer.readBlockPos());
    }

    @Override
    public void handle(MessageResetHolo message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            if (supplier.get().getSender() != null) {
                supplier.get().getSender().level.getCapability(SkinManager.SKINS).ifPresent(SkinData::resetSkins);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
