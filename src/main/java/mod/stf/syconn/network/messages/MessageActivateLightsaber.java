package mod.stf.syconn.network.messages;

import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.ColorConverter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageActivateLightsaber implements IMessage<MessageActivateLightsaber> {

    public MessageActivateLightsaber() {
    }

    @Override
    public void encode(MessageActivateLightsaber message, FriendlyByteBuf buffer) {

    }

    @Override
    public MessageActivateLightsaber decode(FriendlyByteBuf buffer) {
        return new MessageActivateLightsaber();
    }

    @Override
    public void handle(MessageActivateLightsaber message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            ItemStack stack = player.getMainHandItem();
            LightsaberData data = LightsaberHelper.getData(stack);

            if (stack.getItem() instanceof Lightsaber && data != null) {
                data.setState(!data.isActive());
                LightsaberHelper.setData(stack, data);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
