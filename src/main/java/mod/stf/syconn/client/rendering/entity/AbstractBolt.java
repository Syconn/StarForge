package mod.stf.syconn.client.rendering.entity;

import mod.stf.syconn.common.entity.BlasterBolt;
import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractBolt extends ThrowableProjectile {

    public AbstractBolt(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public AbstractBolt(EntityType<? extends ThrowableProjectile> type, LivingEntity entity, Level level) {
        super(type, entity, level);
    }

    protected abstract boolean canBeBlocked();
    protected abstract float damage();

    public boolean isInWater() {
        return false;
    }

    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        float entityYRot = Mth.wrapDegrees(entity.getYRot()) + 720;
        float boltYRot = Mth.wrapDegrees(getYRot()) + 720;

        float compareU = entityYRot + 120;
        float compareD = entityYRot - 120;

        if (canBeBlocked() && entity instanceof Player && ((Player) entity).isBlocking() && ((Player) entity).getUseItem().getItem() instanceof Lightsaber && (boltYRot <= compareU && boltYRot >= compareD)) {
            deflect((Player) entity);
        }

        else if (!entity.is(getOwner()) && entity.hurt(entity.damageSources().source(ModDamage.BLASTER, this, getOwner()), damage())) {
            if (entity.getType() == EntityType.ENDERMAN)
                return;

            if (entity instanceof LivingEntity livingentity1) {
                if (getOwner() instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, getOwner());
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) getOwner(), livingentity1);
                }
            }
        }

        this.discard();
    }

    private void deflect(Player player){
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 100; //distance
        Vec3 vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        HitResult rts = player.getCommandSenderWorld().clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, player));

        if (rts.getType() == HitResult.Type.ENTITY || rts.getType() == HitResult.Type.BLOCK) {

            if (player.getCommandSenderWorld() instanceof ServerLevel) {
                ServerLevel world = (ServerLevel) player.getCommandSenderWorld();
                BlasterBolt b = new BlasterBolt(player, world);
                b.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
                world.addFreshEntity(b);
            }
        }

    }

    protected void onHitBlock(BlockHitResult p_37258_) {
        BlockState blockstate = this.level.getBlockState(p_37258_.getBlockPos());
        blockstate.onProjectileHit(this.level, blockstate, p_37258_, this);
    }

    public boolean isNoGravity() {
        return true;
    }
}
