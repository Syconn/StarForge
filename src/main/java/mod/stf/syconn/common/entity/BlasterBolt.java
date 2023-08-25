package mod.stf.syconn.common.entity;

import mod.stf.syconn.client.rendering.entity.AbstractBolt;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BlasterBolt extends AbstractBolt {

    public BlasterBolt(EntityType<BlasterBolt> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean canBeBlocked() {
        return true;
    }

    @Override
    protected float damage() {
        return 5.3F;
    }

    public BlasterBolt(LivingEntity entity, Level level) {
        super(ModEntities.BLASTER_BOLT.get(), entity, level);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
