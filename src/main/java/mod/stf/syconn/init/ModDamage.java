package mod.stf.syconn.init;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Arrow;

import javax.annotation.Nullable;

public class ModDamage {

    public static DamageSource lightsaber(Entity pSource, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("lightsaber", pSource, pIndirectEntity)).setProjectile();
    }

    public static DamageSource blaster(Entity pSource, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("blaster", pSource, pIndirectEntity)).setProjectile();
    }
}
