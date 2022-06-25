package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.guns.EnergyGun;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> F_11D = REGISTER.register("f11", () -> new EnergyGun(new Item.Properties().tab(StarForge.Tab)));
    public static final RegistryObject<Item> LIGHTSABER = REGISTER.register("lightsaber", Lightsaber::new);
    public static final RegistryObject<Item> STORMTROOPER_EGG = REGISTER.register("stormtrooper", () -> new ForgeSpawnEggItem(ModEntities.STORMTROOPER, 0x000000, 0xFFFFFF, new Item.Properties().tab(StarForge.Tab)));
    public static final RegistryObject<Item> JEDI_EGG = REGISTER.register("jedi", () -> new ForgeSpawnEggItem(ModEntities.JEDI, 0x6C5732, 0x4391CB, new Item.Properties().tab(StarForge.Tab)));
}
