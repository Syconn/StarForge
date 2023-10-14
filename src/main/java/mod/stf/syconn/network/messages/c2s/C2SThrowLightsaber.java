package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.common.entity.ThrownLightsaber;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SThrowLightsaber implements IMessage<C2SThrowLightsaber> {

    public C2SThrowLightsaber() {}

    @Override
    public void encode(C2SThrowLightsaber message, FriendlyByteBuf buffer) {}

    @Override
    public C2SThrowLightsaber decode(FriendlyByteBuf buffer) {
        return new C2SThrowLightsaber();
    }

    @Override
    public void handle(C2SThrowLightsaber message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            ThrownLightsaber lightsaber = new ThrownLightsaber(player, player.getLevel(), player.getMainHandItem());
            lightsaber.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            player.level.addFreshEntity(lightsaber);
            player.getMainHandItem().setCount(0);
        });
        supplier.get().setPacketHandled(true);
    }
}
