package mod.stf.syconn.common.entity;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
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
        return (pEntity.canBeCollidedWith() || pEntity.isPushable()) && !pVehicle.isPassengerOfSameVehicle(pEntity) && !(pEntity instanceof TieBolt);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
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
            if (!canBeControlledByRider()) {
                pPlayer.setYRot(this.getYRot());
                pPlayer.setXRot(this.getXRot());
                pPlayer.startRiding(this);
            } else {
                shootGuns();
            }
        }
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    public boolean canBeControlledByRider() {
        return this.getControllingPassenger() != null;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        LivingEntity livingentity1;
        if (entity instanceof LivingEntity livingentity) {
            livingentity1 = livingentity;
        } else {
            livingentity1 = null;
        }

        return livingentity1;
    }

    public boolean isNoGravity() {
        return canBeControlledByRider();
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
                boolean breaking = false;
                float f = livingentity.xxa * 0.5F;
                float f2 = (float) pTravelVector.y;
                if (livingentity.getXRot() > 9 || livingentity.getXRot() < -9){
                    f2 = (float) (pTravelVector.y + ((livingentity.getXRot() * 0.025) * -1)) * livingentity.zza;
                }
                float f1 = livingentity.zza;
                if (f1 <= 0.0F) {
                    f1 *= 0.25F;
                }

//                this.flyingSpeed = this.getSpeed() * 0.1F;

                float speed = 1.0F;
                //Breaking
                if (f == 0 && f1 < 0){
                    f1 = 0;
                    f2 = 0;

                    breaking = true;
                }

                if (this.isControlledByLocalInstance()) {
                    this.setSpeed(speed);
                    super.travel(new Vec3(f, f2, f1));

                    if (breaking){
                        double x = getDeltaMovement().x;
                        double y = getDeltaMovement().y;
                        double z = getDeltaMovement().z;

                        if (x < 0 || x > 0){
                            x += 0.1 * Mths.flip(x);
                        }

                        if (y < 0 || y > 0){
                            y += 0.1 * Mths.flip(y);
                        }

                        if (z < 0 || z > 0){
                            z += 0.1 * Mths.flip(z);
                        }

                        setDeltaMovement(x, y, z);
                    }
                } else if (livingentity instanceof Player) {
                    this.setDeltaMovement(Vec3.ZERO);
                }

                this.calculateEntityAnimation(true);
                this.tryCheckInsideBlocks();
            } else {
//                this.flyingSpeed = 0.02F;
                super.travel(pTravelVector);
            }
        }
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    protected boolean updateInWaterStateAndDoFluidPushing() {
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
            level.addFreshEntity(bolt);

            TieBolt bolt2 = new TieBolt(player, level);
            bolt2.setPos(Mths.frontPos(this, 0.3f));
            bolt2.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
            level.addFreshEntity(bolt2);
        }
    }
}
