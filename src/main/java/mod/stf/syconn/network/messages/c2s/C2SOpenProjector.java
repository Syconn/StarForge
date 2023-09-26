package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.client.screen.MapScreen;
import mod.stf.syconn.common.blockEntity.MapBe;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SOpenProjector implements IMessage<C2SOpenProjector> {

    private BlockPos pos;

    public C2SOpenProjector() {}

    public C2SOpenProjector(BlockPos pos) {
        this.pos = pos;
    }

    public void encode(C2SOpenProjector message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    public C2SOpenProjector decode(FriendlyByteBuf buffer) {
        return new C2SOpenProjector(buffer.readBlockPos());
    }

    public void handle(C2SOpenProjector message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> Minecraft.getInstance().setScreen(new MapScreen((MapBe) Minecraft.getInstance().level.getBlockEntity(message.pos))));
        supplier.get().setPacketHandled(true);
    }
}
