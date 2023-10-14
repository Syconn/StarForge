package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.blockEntity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrafterBE>> CRAFTER_BE = REGISTER.register("crafter", () -> BlockEntityType.Builder.of(CrafterBE::new, ModBlocks.LIGHTSABER_CRAFTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<HoloBE>> HOLO_BE = REGISTER.register("holo_projector", () -> BlockEntityType.Builder.of(HoloBE::new, ModBlocks.HOLO_PROJECTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SchematicBe>> SCHEMATIC_BE = REGISTER.register("schematic_projector", () -> BlockEntityType.Builder.of(SchematicBe::new, ModBlocks.SCHEMATIC_PROJECTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<MapBe>> MAP_BE = REGISTER.register("map_projector", () -> BlockEntityType.Builder.of(MapBe::new, ModBlocks.MAP_PROJECTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<ProbeBe>> PROBE_BE = REGISTER.register("probe", () -> BlockEntityType.Builder.of(ProbeBe::new, ModBlocks.PROBE.get()).build(null));
}