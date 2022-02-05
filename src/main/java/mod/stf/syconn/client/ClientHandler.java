package mod.stf.syconn.client;

import mod.stf.syconn.Reference;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler
{
    //public static final KeyMapping KEY_CALL_DRONE = new KeyMapping("key.drone.call", GLFW.GLFW_KEY_U, "key.categories.drone");

    public static void setup(FMLClientSetupEvent event){
        //ClientRegistry.registerKeyBinding(KEY_CALL_DRONE);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        //event.registerEntityRenderer(ModEntities.ITEM_DRONE.get(), DroneRender::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        //event.registerLayerDefinition(DroneModelLayers.DRONE, DroneModel::createBodyLayer);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event)
    {
//        if(KEY_CALL_DRONE.isDown() && KEY_CALL_DRONE.consumeClick())
//        {
//            if(Minecraft.getInstance().player != null)
//            {
//
//            }
//        }
    }

}
