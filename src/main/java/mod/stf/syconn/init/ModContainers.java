package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.containers.CrafterContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MOD_ID);
    public static final RegistryObject<MenuType<CrafterContainer>> CRAFTER_CONTAINER = REGISTER.register("crafter",
            () -> IForgeMenuType.create((windowId, inv, data) -> new CrafterContainer(windowId, data.readBlockPos(), inv, inv.player)));
}
