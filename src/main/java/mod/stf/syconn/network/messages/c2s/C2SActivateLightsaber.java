package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.init.ModSounds;
import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SActivateLightsaber implements IMessage<C2SActivateLightsaber> {

    public C2SActivateLightsaber() { }

    public void encode(C2SActivateLightsaber message, FriendlyByteBuf buffer) { }

    public C2SActivateLightsaber decode(FriendlyByteBuf buffer) {
        return new C2SActivateLightsaber();
    }

    public void handle(C2SActivateLightsaber message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            ItemStack stack = player.getMainHandItem();
            LightsaberData data = LightsaberHelper.getData(stack);

            if (stack.getItem() instanceof Lightsaber && data != null) {
                data.setState(!data.isActive());
                if (data.isActive()) player.level.playSound(null, player, ModSounds.LIGHTSABER_IGNITION.get(), SoundSource.PLAYERS, 0.8f, 1.0f);
                else player.level.playSound(null, player, ModSounds.LIGHTSABER_DEACTIVATION.get(), SoundSource.PLAYERS, 0.8f, 1.0f);
                LightsaberHelper.setData(stack, data);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
