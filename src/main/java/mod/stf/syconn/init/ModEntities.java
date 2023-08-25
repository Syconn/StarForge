package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<EntityType<LightsaberEntity>> LIGHTSABER = REGISTER.register("lightsaber", () -> EntityType.Builder.<LightsaberEntity>of(LightsaberEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(100).setUpdateInterval(1).fireImmune().setShouldReceiveVelocityUpdates(true).build("lightsaber"));
    public static final RegistryObject<EntityType<BlasterBolt>> BLASTER_BOLT = REGISTER.register("blaster", () -> EntityType.Builder.<BlasterBolt>of(BlasterBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(100).setUpdateInterval(1).fireImmune().setShouldReceiveVelocityUpdates(true).build("blaster"));
    public static final RegistryObject<EntityType<TieBolt>> TIE_BOLT = REGISTER.register("tie_bolt", () -> EntityType.Builder.<TieBolt>of(TieBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(100).setUpdateInterval(1).fireImmune().setShouldReceiveVelocityUpdates(true).build("tie_bolt"));
    public static final RegistryObject<EntityType<StormTrooper>> STORMTROOPER = REGISTER.register("stormtrooper", () -> EntityType.Builder.of(StormTrooper::new, MobCategory.MONSTER).sized(0.6f, 1.95f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(false).build("stormtrooper"));
    public static final RegistryObject<EntityType<Jedi>> JEDI = REGISTER.register("jedi", () -> EntityType.Builder.of(Jedi::new, MobCategory.AMBIENT).sized(0.6f, 1.95f).clientTrackingRange(8).setShouldReceiveVelocityUpdates(false).build("jedi"));
    public static final RegistryObject<EntityType<TieFighter>> TIE_FIGHTER = REGISTER.register("tie_fighter", () -> EntityType.Builder.<TieFighter>of(TieFighter::new, MobCategory.MISC).sized(2.5F, 2.5F).setTrackingRange(10).build("tie_fighter"));
}