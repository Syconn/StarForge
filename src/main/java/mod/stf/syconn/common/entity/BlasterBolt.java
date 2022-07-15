package mod.stf.syconn.common.entity;

import mod.stf.syconn.client.rendering.entity.AbstractBolt;
import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

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
