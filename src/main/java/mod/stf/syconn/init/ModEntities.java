package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.BlasterBolt;
import mod.stf.syconn.common.entity.Jedi;
import mod.stf.syconn.common.entity.LightsaberEntity;
import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);
    public static final RegistryObject<EntityType<LightsaberEntity>> LIGHTSABER = registerBasic("lightsaber", LightsaberEntity::new);
    public static final RegistryObject<EntityType<BlasterBolt>> BLASTER_BOLT = registerBasic("blaster", BlasterBolt::new);

    public static final RegistryObject<EntityType<StormTrooper>> STORMTROOPER = registerMob("stormtrooper", MobCategory.MONSTER, StormTrooper::new);
    public static final RegistryObject<EntityType<Jedi>> JEDI = registerMob("jedi", MobCategory.CREATURE, Jedi::new);

    private static <T extends Entity> RegistryObject<EntityType<T>> registerBasic(String id, BiFunction<EntityType<T>, Level, T> function)
    {
        EntityType<T> type = EntityType.Builder.of(function::apply, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(100).setUpdateInterval(1).fireImmune().setShouldReceiveVelocityUpdates(true).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String id, MobCategory category, BiFunction<EntityType<T>, Level, T> function)
    {
        EntityType<T> type = EntityType.Builder.of(function::apply, category).sized(1F, 2F).setTrackingRange(100).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(id);
        return REGISTER.register(id, () -> type);
    }
}
