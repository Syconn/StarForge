package mod.stf.syconn.mixin.client;

import mod.stf.syconn.client.audio.ThrownLightsaberSound;
import mod.stf.syconn.common.entity.ThrownLightsaber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "postAddEntitySoundInstance", at = @At(value = "HEAD"))
    private void postAddEntitySoundInstance(Entity pEntity, CallbackInfo ci) {
        if (pEntity instanceof ThrownLightsaber) {
            Minecraft.getInstance().getSoundManager().play(new ThrownLightsaberSound((ThrownLightsaber)pEntity));
        }
    }
}
