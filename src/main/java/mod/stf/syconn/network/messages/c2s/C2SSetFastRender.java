package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetFastRender implements IMessage<C2SSetFastRender> {

    private BlockPos pos;
    private boolean fastRender;

    public C2SSetFastRender() {}

    public C2SSetFastRender(BlockPos pos, boolean fastRender) {
        this.pos = pos;
        this.fastRender = fastRender;
    }

    public void encode(C2SSetFastRender message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
        buffer.writeBoolean(message.fastRender);
    }

    public C2SSetFastRender decode(FriendlyByteBuf buffer) {
        return new C2SSetFastRender(buffer.readBlockPos(), buffer.readBoolean());
    }

    public void handle(C2SSetFastRender message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> supplier.get().getSender().getLevel().getBlockEntity(message.pos, ModBlockEntities.MAP_BE.get()).get().setFastRender(message.fastRender));
        supplier.get().setPacketHandled(true);
    }
}
