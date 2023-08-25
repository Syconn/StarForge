package mod.stf.syconn.world.spawner;

import mod.stf.syconn.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class SpawnHandler {

    private static Map<ResourceLocation, TrooperPatrol> spawners = new HashMap<>();

    @SubscribeEvent
    public static void onWorldLoad(ServerStartingEvent event)
    {
        spawners.put(Level.OVERWORLD.location(), new TrooperPatrol());
    }

    @SubscribeEvent
    public static void onServerStart(ServerStoppedEvent event)
    {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event)
    {
        if(event.phase != TickEvent.Phase.START)
            return;

        if(event.side != LogicalSide.SERVER)
            return;

        TrooperPatrol spawner = spawners.get(event.level.dimension().location());
        if(spawner != null)
        {
            spawner.tick((ServerLevel) event.level, true, true);
        }
    }
}
