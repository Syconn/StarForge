package mod.stf.syconn.client.models.items;

import mod.stf.syconn.Reference;
import mod.stf.syconn.client.models.items.f11.BakedGun;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelBaker {

    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent evt)
    {
        ModelResourceLocation key = new ModelResourceLocation("stf:mbe15_item_chessboard_registry_name", "inventory");
        BakedModel originalModel = evt.getModelRegistry().get(key);
        ModelResourceLocation clipKey = new ModelResourceLocation("stg:accessory_magnifier", "inventory");
        BakedModel originalModelClip = evt.getModelRegistry().get(clipKey);

        if (originalModel == null) {
            System.out.println("Did not find the expected vanilla baked model for ChessboardModel in registry");
        } else if (originalModel instanceof BakedGun) {
            System.out.println("Tried to replace ChessboardModel twice");
        } else {
            evt.getModelRegistry().put(key, new BakedGun(evt.getModelLoader(), originalModel, originalModelClip));
        }
    }
}
