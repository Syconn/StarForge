package mod.stf.syconn.common;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.Jedi;
import mod.stf.syconn.common.entity.StormTrooper;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.world.data.SkinData;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonHandler {

    public CommonHandler() {
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STORMTROOPER.get(), StormTrooper.prepareAttributes().build());
        event.put(ModEntities.JEDI.get(), Jedi.createAttributes().build());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesLevel(AttachCapabilitiesEvent<Level> event){
        if (!event.getObject().getCapability(SkinManager.SKINS).isPresent()) {
            event.addCapability(new ResourceLocation(Reference.MOD_ID, "skinmanager"), new SkinManager());
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SkinData.class);
    }
}
