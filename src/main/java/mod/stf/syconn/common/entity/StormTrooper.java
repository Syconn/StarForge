package mod.stf.syconn.common.entity;

import mod.stf.syconn.common.entity.goal.NearestAttackablePatrolTargetGoal;
import mod.stf.syconn.common.entity.goal.PatrolGoal;
import mod.stf.syconn.common.entity.goal.RangedGunAttackGoal;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.GunItem;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class StormTrooper extends AbstractSkeleton {

    public static final EntityDataAccessor<Boolean> DATA_LEADER = SynchedEntityData.defineId(StormTrooper.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    private BlockPos target;
    private boolean leader = false;
    private boolean patrolling;

    public StormTrooper(EntityType<StormTrooper> type,  Level lvl) {
        super(type, lvl);
        this.getEntityData().set(DATA_LEADER, false);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_LEADER, false);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new RangedGunAttackGoal<>(this, 1.0D, 5, 30.0F));
        this.goalSelector.addGoal(2, new PatrolGoal(this, 0.712D, 0.868D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackablePatrolTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackablePatrolTargetGoal<>(this, Jedi.class, true));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.MOVEMENT_SPEED, (double)0.23F);
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.WITHER_SKELETON_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return super.getHurtSound(pDamageSource);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    public boolean isPatrolling() {
        return patrolling;
    }

    public boolean isLeader() {
        return this.getEntityData().get(DATA_LEADER);
    }

    public boolean getLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
        this.getEntityData().set(DATA_LEADER, leader);
    }

    public boolean hasPatrolTarget(){
        return target != null;
    }

    public void setPatrollingTarget(BlockPos target){
        this.target = target;
    }

    @Nullable
    public BlockPos getPatrolTarget() {
        return target;
    }

    public void findPatrolTarget() {
        this.target = this.blockPosition().offset(-500 + this.random.nextInt(750), 0, -500 + this.random.nextInt(750));
        this.patrolling = true;
    }

    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();
        if (entity instanceof Creeper) {
            Creeper creeper = (Creeper)entity;
            if (creeper.canDropMobsSkull()) {
                creeper.increaseDroppedSkulls();
                this.spawnAtLocation(Items.WITHER_SKELETON_SKULL);
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (this.isLeader()) {
            //this.setItemSlot(EquipmentSlot.HEAD, Raid.getLeaderBannerInstance());
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected void populateDefaultEquipmentEnchantments(DifficultyInstance pDifficulty) {

    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.F_11D.get()));
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        if (this.getMainHandItem().getItem() instanceof GunItem) {
            ThrowableProjectile bolt = ((GunItem) this.getMainHandItem().getItem()).createBullet(this);
            double d0 = pTarget.getX() - this.getX();
            double d1 = pTarget.getY(0.3333333333333333D) - bolt.getY();
            double d2 = pTarget.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double)0.2F;
            bolt.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
            this.level.addFreshEntity(bolt);
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.target != null) {
            pCompound.put("PatrolTarget", NbtUtils.writeBlockPos(this.target));
        }

        pCompound.putBoolean("PatrolLeader", leader);
        this.getEntityData().set(DATA_LEADER, leader);

        pCompound.putBoolean("Patrolling", this.patrolling);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("PatrolTarget")) {
            this.target = NbtUtils.readBlockPos(pCompound.getCompound("PatrolTarget"));
        }

        this.leader = pCompound.getBoolean("PatrolLeader");
        this.patrolling = pCompound.getBoolean("Patrolling");
    }
}
