package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSlimSkin implements IMessage<C2SSlimSkin> {

    private boolean slim;
    private BlockPos pos;

    public C2SSlimSkin() {
    }

    public C2SSlimSkin(boolean slim, BlockPos pos) {
        this.slim = slim;
        this.pos = pos;
    }

    @Override
    public void encode(C2SSlimSkin message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.slim);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public C2SSlimSkin decode(FriendlyByteBuf buffer) {
        return new C2SSlimSkin(buffer.readBoolean(), buffer.readBlockPos());
    }

    @Override
    public void handle(C2SSlimSkin message, Supplier<NetworkEvent.Context> supplier) {
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
