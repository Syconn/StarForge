package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.blockEntity.CrafterBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrafterBE>> CRAFTER_BE = REGISTER.register("crafter", () -> BlockEntityType.Builder.of(CrafterBE::new, ModBlocks.LIGHTSABER_CRAFTER.get()).build(null));
}
