package mod.stf.syconn.world.spawner;

import mod.stf.syconn.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.PatrolSpawner;
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
        MinecraftServer server = event.getServer();
        spawners.put(DimensionType.OVERWORLD_LOCATION.location(), new TrooperPatrol());
        //spawners.put(DimensionType.NETHER_LOCATION.location(), new GoblinTraderSpawner(server, "VeinGoblinTrader", ModEntities.VEIN_GOBLIN_TRADER.get(), Config.COMMON.veinGoblinTrader));
    }

    @SubscribeEvent
    public static void onServerStart(ServerStoppedEvent event)
    {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if(event.phase != TickEvent.Phase.START)
            return;

        if(event.side != LogicalSide.SERVER)
            return;

        TrooperPatrol spawner = spawners.get(event.world.dimension().location());
        if(spawner != null)
        {
            spawner.tick((ServerLevel) event.world, true, true);
        }
    }
}
