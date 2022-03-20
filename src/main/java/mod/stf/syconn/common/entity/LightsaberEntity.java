package mod.stf.syconn.common.entity;

import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class LightsaberEntity extends ThrowableItemProjectile {

    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(LightsaberEntity.class, EntityDataSerializers.ITEM_STACK);
    public int spin;
    private boolean returning = false;

    public LightsaberEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
        this.getEntityData().set(DATA_ITEM_STACK, LightsaberHelper.createDefaults().get(1));
    }

    public LightsaberEntity(LivingEntity entity, Level level, ItemStack item) {
        super(ModEntities.LIGHTSABER.get(), entity, level);
        this.getEntityData().set(DATA_ITEM_STACK, item.copy());
    }

    protected void defineSynchedData() {
        this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putBoolean("return", this.returning);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.returning = pCompound.getBoolean("return");
    }

    @Override
    public void tick() {
        spin+=60;
        if (spin >= 360)
            spin = 0;

        Entity entity = this.getOwner();
        if (entity != null) {
            if (distanceTo(entity) >= 10.0f)
                returning = true;

            if (returning) {
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(0.8D)));
            }

            if (returning && distanceTo(getOwner()) <= 1.8f) {
                if (getOwner() instanceof Player player) {
                    player.addItem(this.getEntityData().get(DATA_ITEM_STACK));
                }

                this.discard();
            }
        }

        //TODO GIVE OFF LIGHT
        //TODO FLYING SOUND
        //TODO TEST DAMAGE

        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        if (!entity.is(getOwner()) && entity.hurt(ModDamage.lightsaber(this, getOwner()), 8.0F)) {
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
    }

    public void playerTouch(Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    public ItemStack getItem() {
        return LightsaberHelper.activate(this.getEntityData().get(DATA_ITEM_STACK));
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTSABER.get();
    }
}
