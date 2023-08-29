package mod.stf.syconn.mixin;

import mod.stf.syconn.init.ModSounds;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "attack", at = @At(value = "HEAD"))
    public void attack(Entity pTarget, CallbackInfo ci) {
        if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(((Player)(Object) this), pTarget)) return;
        if (pTarget.isAttackable() && ((Player)(Object) this).getMainHandItem().getItem() instanceof Lightsaber) {
            ((Player)(Object) this).playSound(ModSounds.LIGHTSABER_SWING.get(), 1.0F, 0.8F);
        }
    }
}
