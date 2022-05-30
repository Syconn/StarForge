package mod.stf.syconn.common.entity;

import mod.stf.syconn.api.entity.RandomMobTexture;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class AbstractForceUser extends RandomMobTexture {

    protected AbstractForceUser(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
}
