package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.common.entity.TieFighter;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SShootGuns implements IMessage<C2SShootGuns> {

    public C2SShootGuns() {
    }

    @Override
    public void encode(C2SShootGuns message, FriendlyByteBuf buffer) {

    }

    @Override
    public C2SShootGuns decode(FriendlyByteBuf buffer) {
        return new C2SShootGuns();
    }

    @Override
    public void handle(C2SShootGuns message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null){
                Entity vehicle = player.getVehicle();
                if (vehicle instanceof TieFighter){
                    ((TieFighter) vehicle).shootGuns();
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
