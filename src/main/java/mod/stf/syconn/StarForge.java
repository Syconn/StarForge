package mod.stf.syconn;

import mod.stf.syconn.client.ClientHandler;
import mod.stf.syconn.common.CommonHandler;
import mod.stf.syconn.init.*;
import mod.stf.syconn.network.Network;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        ModBlocks.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);
        ModBlockEntities.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModContainers.REGISTER.register(bus);
    }

    public static CreativeModeTab Tab = new CreativeModeTab("StarForge") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.F_11D.get().getDefaultInstance();
        }
    };

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        Network.init();
        MinecraftForge.EVENT_BUS.register(new CommonHandler());
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }
}