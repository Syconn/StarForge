package mod.stf.syconn.client;

import mod.stf.syconn.Reference;
import mod.stf.syconn.client.screen.ColorScreen;
import mod.stf.syconn.client.screen.HiltScreen;
import mod.stf.syconn.client.rendering.entity.LightsaberRenderer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.Lightsaber;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageActivateLightsaber;
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

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIGHTSABER.get(), LightsaberRenderer::new);
    }
}
