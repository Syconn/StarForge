package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.SchematicItem;
import mod.stf.syconn.item.TieItem;
import mod.stf.syconn.item.guns.EnergyGun;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> F_11D = REGISTER.register("f11", () -> new EnergyGun(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSABER = REGISTER.register("lightsaber", Lightsaber::new);
    public static final RegistryObject<Item> SCHEMATIC_ITEM = REGISTER.register("schematic_item", SchematicItem::new);
    public static final RegistryObject<Item> STORMTROOPER_EGG = REGISTER.register("stormtrooper", () -> new ForgeSpawnEggItem(ModEntities.STORMTROOPER, 0x000000, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> JEDI_EGG = REGISTER.register("jedi", () -> new ForgeSpawnEggItem(ModEntities.JEDI, 0x6C5732, 0x4391CB, new Item.Properties()));
    public static final RegistryObject<Item> TIE_ITEM = REGISTER.register("tie", TieItem::new);

    public static void addItems(CreativeModeTab.Output e) {
        e.acceptAll(LightsaberHelper.createDefaults());
        REGISTER.getEntries().forEach(item -> {
            if (!(item.get() instanceof Lightsaber)) e.accept(new ItemStack(item.get()));
        });
    }
}
