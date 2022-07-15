package mod.stf.syconn.common.entity;

import mod.stf.syconn.client.rendering.entity.AbstractBolt;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class TieBolt extends AbstractBolt {

    public TieBolt(EntityType<TieBolt> type, Level level) {
        super(type, level);
    }

    public TieBolt(LivingEntity entity, Level level) {
        super(ModEntities.TIE_BOLT.get(), entity, level);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected float damage() {
        return 18F;
    }

    @Override
    protected boolean canBeBlocked() {
        return false;
    }

    @Override
    protected void defineSynchedData() {

    }
}
