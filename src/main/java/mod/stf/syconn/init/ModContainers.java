package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.common.containers.HoloContainer;
import mod.stf.syconn.common.containers.SchematicContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Reference.MOD_ID);
    public static final RegistryObject<MenuType<ColorContainer>> COLOR_CONTAINER = REGISTER.register("color",
            () -> IForgeMenuType.create((windowId, inv, data) -> new ColorContainer(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<HiltContainer>> HILT_CONTAINER = REGISTER.register("hilt",
            () -> IForgeMenuType.create((windowId, inv, data) -> new HiltContainer(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<SchematicContainer>> SCHEM_CONTAINER = REGISTER.register("schem",
            () -> IForgeMenuType.create((windowId, inv, data) -> new SchematicContainer(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<HoloContainer>> HOLO_CONTAINER = REGISTER.register("holo",
            () -> IForgeMenuType.create((windowId, inv, data) -> new HoloContainer(windowId, data.readBlockPos(), inv, inv.player)));
}
