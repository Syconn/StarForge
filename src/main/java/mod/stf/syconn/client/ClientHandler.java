package mod.stf.syconn.client;

import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.ApplicationScreen;
import mod.stf.syconn.client.rendering.blockentity.HoloRender;
import mod.stf.syconn.client.rendering.blockentity.NavRender;
import mod.stf.syconn.client.rendering.blockentity.SchematicRender;
import mod.stf.syconn.client.rendering.entity.*;
import mod.stf.syconn.client.rendering.model.BlockModel;
import mod.stf.syconn.client.rendering.model.BoltModel;
import mod.stf.syconn.client.rendering.model.PlayerLikeModel;
import mod.stf.syconn.client.rendering.model.TieModel;
import mod.stf.syconn.client.screen.ColorScreen;
import mod.stf.syconn.client.screen.HiltScreen;
import mod.stf.syconn.client.screen.HoloScreen;
import mod.stf.syconn.client.screen.SchematicScreen;
import mod.stf.syconn.common.entity.TieFighter;
import mod.stf.syconn.init.*;
import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageActivateLightsaber;
import mod.stf.syconn.network.messages.MessageShootGuns;
import mod.stf.syconn.network.messages.MessageThrowLightsaber;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static final KeyMapping KEY_LIGHTSABER_ACTIVATE = new KeyMapping("key.lightsaber.activate", GLFW.GLFW_KEY_V, "key.categories.stf");

    public static void setup(){
        ClientRegistry.registerKeyBinding(KEY_LIGHTSABER_ACTIVATE);

        MenuScreens.register(ModContainers.COLOR_CONTAINER.get(), ColorScreen::new);
        MenuScreens.register(ModContainers.HILT_CONTAINER.get(), HiltScreen::new);
        MenuScreens.register(ModContainers.SCHEM_CONTAINER.get(), SchematicScreen::new);
        MenuScreens.register(ModContainers.NAV_CONTAINER.get(), ApplicationScreen::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHTSABER_CRAFTER.get(), RenderType.translucent());

        registerProperties();
    }

    public static void registerProperties(){
        ItemProperties.register(ModItems.LIGHTSABER.get(), new ResourceLocation("model"), new ItemPropertyFunction(){

            @Override
            public float call(ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                if (LightsaberHelper.getData(pStack) != null){
                    return LightsaberHelper.getData(pStack).getHandle().getId();
                }

                return 1f;
            }
        });
        ItemProperties.register(ModItems.LIGHTSABER.get(), new ResourceLocation("active"), new ItemPropertyFunction(){

            @Override
            public float call(ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                if (LightsaberHelper.getData(pStack) != null){
                    return LightsaberHelper.getData(pStack).isActive() ? 1.0f : 0.0f;
                }

                return 1.0f;
            }
        });
    }

    @SubscribeEvent
    public static void registerColors(final ColorHandlerEvent.Item event) {
        // LightsaberHelper.getData(pStack) != null ? LightsaberHelper.getData(pStack).getColor().getDecimal() : -1
        event.getItemColors().register(((pStack, pTintIndex) -> pStack.getHoverName().getContents().equals("rainbow") ? LightsaberHelper.getData(pStack).getColor().rainbow(pStack) : LightsaberHelper.getData(pStack).getColor().getDecimal()), ModItems.LIGHTSABER.get());
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event)
    {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player != null) {
            if(KEY_LIGHTSABER_ACTIVATE.isDown() && KEY_LIGHTSABER_ACTIVATE.consumeClick())
            {
                Network.getPlayChannel().sendToServer(new MessageActivateLightsaber());
            }
            if (Minecraft.getInstance().options.keyDrop.isDown() && player.isShiftKeyDown() && player.getMainHandItem().getItem() instanceof Lightsaber){
                Network.getPlayChannel().sendToServer(new MessageThrowLightsaber());
                Minecraft.getInstance().options.keyDrop.consumeClick();
            }
        }
    }

//    @SubscribeEvent
//    public void onMousePress(InputEvent.MouseInputEvent event) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        if (Minecraft.getInstance().options.keyAttack.isDown() && player.isPassenger() && player.getVehicle() instanceof TieFighter){
//            //Network.getPlayChannel().sendToServer(new MessageShootGuns());
//        }
//    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIGHTSABER.get(), LightsaberRenderer::new);
        event.registerEntityRenderer(ModEntities.BLASTER_BOLT.get(), BlasterBoltRenderer::new);
        event.registerEntityRenderer(ModEntities.STORMTROOPER.get(), StormTrooperRender::new);
        event.registerEntityRenderer(ModEntities.JEDI.get(), JediRender::new);
        event.registerEntityRenderer(ModEntities.TIE_FIGHTER.get(), TieFighterRenderer::new);
        event.registerEntityRenderer(ModEntities.TIE_BOLT.get(), TieBoltRender::new);
        event.registerEntityRenderer(ModEntities.MOVING_BLOCK.get(), BlockRender::new);

        event.registerBlockEntityRenderer(ModBlockEntities.HOLO_BE.get(), HoloRender::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SCHEMATIC_BE.get(), SchematicRender::new);
        event.registerBlockEntityRenderer(ModBlockEntities.NAV_BE.get(), NavRender::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BoltModel.LAYER_LOCATION, BoltModel::createBodyLayer);
        event.registerLayerDefinition(PlayerLikeModel.MODEL, PlayerLikeModel::createBodyLayer);
        event.registerLayerDefinition(TieModel.LAYER_LOCATION, TieModel::createBodyLayer);
        event.registerLayerDefinition(BlockModel.LAYER_LOCATION, BlockModel::createBodyLayer);
    }
}
