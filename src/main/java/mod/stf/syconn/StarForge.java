package mod.stf.syconn;

import mod.stf.syconn.client.ClientHandler;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.network.Network;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class StarForge {

    public StarForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        ModItems.REGISTER.register(bus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        Network.init();
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        ClientHandler.setup(event);
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }
}