package mod.stf.syconn.item.guns;

import mod.stf.syconn.common.entity.BlasterBolt;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.init.ModSounds;
import mod.stf.syconn.item.GunItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;

public class EnergyGun extends GunItem {

    //TODO FIX PLACING OF BULLET

    public EnergyGun(Properties pProperties) {
        super(pProperties, 20);
    }

    public ThrowableProjectile createBullet(LivingEntity player) {
        return new BlasterBolt(player, player.getLevel());
    }

    public SoundEvent shootSound() {
        return ModSounds.BLASTER_SHOOT.get();
    }

    public static ItemStack create() {
        ItemStack stack = new ItemStack(ModItems.F_11D.get());
        stack.getOrCreateTag().putInt(HEAT, 20);
        return stack;
    }
}
