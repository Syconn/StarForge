package mod.stf.syconn.common.entity;

import mod.stf.syconn.api.entity.Vehicle;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TieFighter extends Mob implements PlayerRideableJumping {

    public TieFighter(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TieFighter(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.TIE_FIGHTER.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.MOVEMENT_SPEED, (double)1F);
    }

    @Override
    public double getMyRidingOffset() {
        return 0;
    }

    @Override
    public void positionRider(Entity pPassenger) {
        super.positionRider(pPassenger);
        pPassenger.setPos(new Vec3(this.getX(), this.getY() - 1, this.getZ()));
        if (pPassenger instanceof LivingEntity) {
            float f3 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
            float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
            float f1 = 0.2F;
            float f2 = 0.12F;
            pPassenger.setPos(this.getX() + (double)(f1 * f3), this.getY() + 0.2 + (double)f2, this.getZ() - (double)(f1 * f));
            if (pPassenger instanceof LivingEntity) {
                ((LivingEntity)pPassenger).yBodyRot = this.yBodyRot + 180;
            }
        }
    }

    protected boolean canAddPassenger(Entity pPassenger) {
        return this.getPassengers().size() < 1;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean canCollideWith(Entity pEntity) {
        return canVehicleCollide(this, pEntity);
    }

    public static boolean canVehicleCollide(Entity pVehicle, Entity pEntity) {
        return (pEntity.canBeCollidedWith() || pEntity.isPushable()) && !pVehicle.isPassengerOfSameVehicle(pEntity);
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddMobPacket(this);
    }

    @Override
    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        if (pDamageSource.getEntity() != getControllingPassenger())
            super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {}

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        this.spawnAtLocation(ModItems.TIE_ITEM.get());
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!this.level.isClientSide) {
            pPlayer.setYRot(this.getYRot());
            pPlayer.setXRot(this.getXRot());
            pPlayer.startRiding(this);
        }
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    @Override
    public boolean canBeControlledByRider() {
        return this.getControllingPassenger() instanceof LivingEntity;
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public boolean isNoGravity() {
        //return canBeControlledByRider();
        return true;
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isAlive()) {
            //System.out.println(isVehicle());
            if (this.isVehicle() && this.canBeControlledByRider()) {
                LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot());
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float f = livingentity.xxa * 0.5F;
                float f2 = (float) pTravelVector.y;
                if (livingentity.getXRot() > 9 || livingentity.getXRot() < -9){
                    f2 = (float) (pTravelVector.y + ((livingentity.getXRot() * 0.025) * -1)) * livingentity.zza;
                }
                float f1 = livingentity.zza;
                if (f1 <= 0.0F) {
                    f1 *= 0.25F;
                }

                this.flyingSpeed = this.getSpeed() * 0.1F;
                if (this.isControlledByLocalInstance()) {
                    //this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.setSpeed(1.0F);
                    super.travel(new Vec3(f, f2, f1));
                } else if (livingentity instanceof Player) {
                    this.setDeltaMovement(Vec3.ZERO);
                }

                this.calculateEntityAnimation(this, false);
                this.tryCheckInsideBlocks();
            } else {
                this.flyingSpeed = 0.02F;
                super.travel(pTravelVector);
            }
        }
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void onPlayerJump(int pJumpPower) {

    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public void handleStartJump(int pJumpPower) {

    }

    @Override
    public void handleStopJump() {

    }

    public void shootGuns(){
        if (!level.isClientSide() && getControllingPassenger() instanceof Player){
            float f2 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
            float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
            float f3 = Mth.cos(this.getXRot() * ((float)Math.PI / 180F));

            Player player = (Player) getControllingPassenger();
            TieBolt bolt = new TieBolt(player, level);
            bolt.setPos(Mths.frontPos(this, -0.3f));
            bolt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
            //level.addFreshEntity(bolt);

            TieBolt bolt2 = new TieBolt(player, level);
            bolt2.setPos(Mths.frontPos(this, 0.5f));
            bolt2.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
            level.addFreshEntity(bolt2);
        }
    }
}
