package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetRender implements IMessage<C2SSetRender> {

    private BlockPos pos;
    private BlockPos target;

    public C2SSetRender() {}

    public C2SSetRender(BlockPos pos, BlockPos target) {
        this.pos = pos;
        this.target = target;
    }

    public void encode(C2SSetRender message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
        buffer.writeBlockPos(message.target);
    }

    public C2SSetRender decode(FriendlyByteBuf buffer) {
        return new C2SSetRender(buffer.readBlockPos(), buffer.readBlockPos());
    }

    public void handle(C2SSetRender message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            Level level = supplier.get().getSender().getLevel();
            level.getBlockEntity(message.pos, ModBlockEntities.MAP_BE.get()).get().setSelectedPos(message.target, level);
        });
        supplier.get().setPacketHandled(true);
    }
}
