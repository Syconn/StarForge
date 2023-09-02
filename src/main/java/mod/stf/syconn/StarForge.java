package mod.stf.syconn;

import mod.stf.syconn.client.ClientHandler;
import mod.stf.syconn.common.CommonHandler;
import mod.stf.syconn.init.*;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;

@Mod(Reference.MOD_ID)
public class StarForge {

    public StarForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(Reference.MOD_ID), Reference.MOD_ID);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON, Reference.MOD_ID + "/common.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::createTab));
        ModBlocks.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);
        ModBlockEntities.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModContainers.REGISTER.register(bus);
        ModRecipeSerializers.REGISTER.register(bus);
        ModRecipes.REGISTER.register(bus);
        ModSounds.REGISTER.register(bus);
    }

    public void createTab(CreativeModeTabEvent.Register e) {
        e.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "starwars"), builder -> builder.noScrollBar().title(Component.translatable("itemGroup.StarForge")).icon(() -> LightsaberHelper.customOffLightsaber(LightsaberData.HandleType.MACE, LColor.of(DyeColor.PURPLE), true)).displayItems((a, p) -> ModItems.addItems(p)).build());
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        Network.init();
        MinecraftForge.EVENT_BUS.register(new CommonHandler());
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setup);
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }
}