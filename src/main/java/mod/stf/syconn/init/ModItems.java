package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.item.GunItem;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> F_11D = REGISTER.register("f11", () -> new GunItem(new Item.Properties().tab(StarForge.Tab)));
    public static final RegistryObject<Item> Lightsaber = REGISTER.register("lightsaber", Lightsaber::new);

}
