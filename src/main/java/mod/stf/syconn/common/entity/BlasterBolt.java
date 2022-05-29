package mod.stf.syconn.common.entity;

import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class BlasterBolt extends ThrowableProjectile {

    public BlasterBolt(EntityType<? extends ThrowableProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public BlasterBolt(LivingEntity entity, Level level) {
        super(ModEntities.BLASTER_BOLT.get(), entity, level);
        //this.setPos(entity.getX(), entity.getEyeY() - (entity.getEyeHeight() / 2), entity.getZ());
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        if (!entity.is(getOwner()) && entity.hurt(ModDamage.lightsaber(this, getOwner()), 5.3F)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (entity instanceof LivingEntity livingentity1) {
                if (getOwner() instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, getOwner());
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) getOwner(), livingentity1);
                }
            }
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        BlockState blockstate = this.level.getBlockState(p_37258_.getBlockPos());
        blockstate.onProjectileHit(this.level, blockstate, p_37258_, this);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
