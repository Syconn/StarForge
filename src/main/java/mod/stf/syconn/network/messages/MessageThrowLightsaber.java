package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.entity.LightsaberEntity;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageThrowLightsaber implements IMessage<MessageThrowLightsaber> {

    public MessageThrowLightsaber() {}

    @Override
    public void encode(MessageThrowLightsaber message, FriendlyByteBuf buffer) {}

    @Override
    public MessageThrowLightsaber decode(FriendlyByteBuf buffer) {
        return new MessageThrowLightsaber();
    }

    @Override
    public void handle(MessageThrowLightsaber message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            LightsaberEntity lightsaber = new LightsaberEntity(player, player.getLevel(), player.getMainHandItem());
            lightsaber.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            player.level.addFreshEntity(lightsaber);
            player.getMainHandItem().setCount(0);
        });
        supplier.get().setPacketHandled(true);
    }
}
