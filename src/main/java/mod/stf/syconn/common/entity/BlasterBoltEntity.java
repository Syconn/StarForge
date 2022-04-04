package mod.stf.syconn.common.entity;

import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class BlasterBoltEntity extends ThrowableProjectile {

    public BlasterBoltEntity(EntityType<? extends ThrowableProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public BlasterBoltEntity(LivingEntity entity, Level level) {
        super(ModEntities.BLASTER_BOLT.get(), entity, level);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
