package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.network.messages.IMessage;
import mod.stf.syconn.world.data.SkinData;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SResetHolo implements IMessage<C2SResetHolo> {

    private BlockPos pos;

    public C2SResetHolo() {
    }

    public C2SResetHolo(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encode(C2SResetHolo message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public C2SResetHolo decode(FriendlyByteBuf buffer) {
        return new C2SResetHolo(buffer.readBlockPos());
    }

    @Override
    public void handle(C2SResetHolo message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            if (supplier.get().getSender() != null) {
                supplier.get().getSender().level.getCapability(SkinManager.SKINS).ifPresent(SkinData::resetSkins);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
