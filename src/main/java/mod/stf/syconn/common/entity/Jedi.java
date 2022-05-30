package mod.stf.syconn.common.entity;

import mod.stf.syconn.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Jedi extends AbstractForceUser {

    public Jedi(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public boolean canAttackType(EntityType<?> pType) {
        return pType != EntityType.CREEPER && super.canAttackType(pType);
    }

    @Override
    public ResourceLocation[] getTextures() {
        return new ResourceLocation[]{ new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi1.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi2.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi3.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi4.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi6.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi7.png"), new ResourceLocation(Reference.MOD_ID, "textures/entity/jedi/jedi8.png")};
    }
}