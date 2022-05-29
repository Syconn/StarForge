package mod.stf.syconn.common;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.StormTrooper;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonHandler {

    public CommonHandler() {
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STORMTROOPER.get(), StormTrooper.prepareAttributes().build());
    }
}
