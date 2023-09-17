package mod.stf.syconn.common;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.Jedi;
import mod.stf.syconn.common.entity.StormTrooper;
import mod.stf.syconn.common.entity.TieFighter;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.world.data.ChunkData;
import mod.stf.syconn.world.data.ChunkManager;
import mod.stf.syconn.world.data.SkinData;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonHandler {

    public CommonHandler() {}

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STORMTROOPER.get(), StormTrooper.prepareAttributes().build());
        event.put(ModEntities.TIE_FIGHTER.get(), TieFighter.prepareAttributes().build());
        event.put(ModEntities.JEDI.get(), Jedi.createAttributes().build());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesLevel(AttachCapabilitiesEvent<Level> event){
        if (!event.getObject().getCapability(SkinManager.SKINS).isPresent())
            event.addCapability(new ResourceLocation(Reference.MOD_ID, "skinmanager"), new SkinManager());
        if (!event.getObject().getCapability(ChunkManager.CHUNKS).isPresent())
            event.addCapability(new ResourceLocation(Reference.MOD_ID, "chunkmanager"), new ChunkManager());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SkinData.class);
        event.register(ChunkData.class);
    }


}
