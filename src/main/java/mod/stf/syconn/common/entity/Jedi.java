package mod.stf.syconn.common.entity;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Jedi extends AbstractForceUser implements RangedAttackMob {

    public Jedi(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new RangedAttackGoal(this, 2.0D, 5, 30.0f));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.0D, true));
        goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, StormTrooper.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public boolean canAttackType(EntityType<?> pType) {
        return pType != EntityType.CREEPER && super.canAttackType(pType);
    }

    @Override
    public ResourceLocation[] getTextures() {
        return new ResourceLocation[]{ new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi2.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi3.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi4.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi6.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi7.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi8.png")};
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        populateDefaultEquipmentSlots(pLevel.getRandom(), pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }



    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, LightsaberHelper.randomPreset(true));
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {

    }
}