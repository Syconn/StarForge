package mod.stf.syconn.mixin;

import mod.stf.syconn.init.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "handleEntityEvent", at = @At(value = "HEAD"), cancellable = true)
    private void handleEntityData(byte id, CallbackInfo ci) {
        if (id == 29) {
            ((LivingEntity)(Object) this).playSound(ModSounds.LIGHTSABER_BLOCK.get(), 1.0F, 0.8F);
            ci.cancel();
        }
    }
}
