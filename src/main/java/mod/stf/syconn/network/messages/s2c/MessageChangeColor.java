package mod.stf.syconn.network.messages.s2c;

import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageChangeColor implements IMessage<MessageChangeColor> {

    private int color;

    public MessageChangeColor() {}

    public MessageChangeColor(int color) {
        this.color = color;
    }

    @Override
    public void encode(MessageChangeColor message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.color);
    }

    @Override
    public MessageChangeColor decode(FriendlyByteBuf buffer) {
        return new MessageChangeColor(buffer.readInt());
    }

    @Override
    public void handle(MessageChangeColor message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if(!(player.containerMenu instanceof ColorContainer))
                return;

            ColorContainer crafter = (ColorContainer) player.containerMenu;
            LazyOptional<IItemHandler> cap = crafter.getBlockEntity().getCapability(ForgeCapabilities.ITEM_HANDLER);

            if (cap.isPresent()) {
                IItemHandler handler = cap.resolve().get();
                LightsaberData data = LightsaberHelper.getData(handler.getStackInSlot(0)).setColor(new LColor(message.color));
                LightsaberHelper.setData(handler.getStackInSlot(0), data);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
