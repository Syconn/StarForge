package mod.stf.syconn.client.rendering.entity;

import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.PlayerLikeModel;
import mod.stf.syconn.common.entity.Jedi;
import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class JediRender extends HumanoidMobRenderer<Jedi, PlayerLikeModel<Jedi>> {

    public JediRender(EntityRendererProvider.Context context) {
        super(context, new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL)), new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL))));
    }

    @Override
    public ResourceLocation getTextureLocation(Jedi entity) {
        return entity.getTexture();
    }
}