package mod.stf.syconn.network.messages.s2c;

import mod.stf.syconn.common.entity.TieFighter;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageShootGuns implements IMessage<MessageShootGuns> {

    public MessageShootGuns() {
    }

    @Override
    public void encode(MessageShootGuns message, FriendlyByteBuf buffer) {

    }

    @Override
    public MessageShootGuns decode(FriendlyByteBuf buffer) {
        return new MessageShootGuns();
    }

    @Override
    public void handle(MessageShootGuns message, Supplier<NetworkEvent.Context> supplier) {
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
