package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetName implements IMessage<C2SSetName> {

    private BlockPos pos;
    private Component name;

    public C2SSetName() {}

    public C2SSetName(BlockPos pos, Component name) {
        this.pos = pos;
        this.name = name;
    }

    public void encode(C2SSetName message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
        buffer.writeComponent(message.name);
    }

    public C2SSetName decode(FriendlyByteBuf buffer) {
        return new C2SSetName(buffer.readBlockPos(), buffer.readComponent());
    }

    public void handle(C2SSetName message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            supplier.get().getSender().getLevel().getBlockEntity(message.pos, ModBlockEntities.PROBE_BE.get()).get().setCustomName(message.name);
            System.out.println(supplier.get().getSender().getLevel().getBlockEntity(message.pos, ModBlockEntities.PROBE_BE.get()).get().getName());
        });
        supplier.get().setPacketHandled(true);
    }
}
