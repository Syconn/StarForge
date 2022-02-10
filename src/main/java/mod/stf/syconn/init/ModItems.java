package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.item.Chessboard;
import mod.stf.syconn.item.GunItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.IForgeBakedModel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Chessboard> F11 = REGISTER.register("mbe15_item_chessboard_registry_name", Chessboard::new);
    public static final RegistryObject<Item> GUN_ITEM = REGISTER.register("accessory_magnifier", () -> new GunItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
