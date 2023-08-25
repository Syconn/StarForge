package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.block.HoloProjector;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.block.SchematicProjector;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties();

    public static final RegistryObject<Block> LIGHTSABER_CRAFTER = REGISTER.register("lightsaber_crafter", LightsaberCrafter::new);
    public static final RegistryObject<Item> CRAFTER_ITEM = fromBlock(LIGHTSABER_CRAFTER);
    public static final RegistryObject<HoloProjector> HOLO_PROJECTOR = REGISTER.register("holo_projector", HoloProjector::new);
    public static final RegistryObject<Item> HOLO_ITEM = fromBlock(HOLO_PROJECTOR);
    public static final RegistryObject<SchematicProjector> SCHEMATIC_PROJECTOR = REGISTER.register("schematic_projector", SchematicProjector::new);
    public static final RegistryObject<Item> SCHEMATIC_ITEM = fromBlock(SCHEMATIC_PROJECTOR);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ModItems.REGISTER.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
