package mod.stf.syconn.client.rendering.entity;

import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.PlayerLikeModel;
import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class StormTrooperRender extends HumanoidMobRenderer<StormTrooper, PlayerLikeModel<StormTrooper>> {

    public StormTrooperRender(EntityRendererProvider.Context context) {
        super(context, new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL)), new PlayerLikeModel(context.bakeLayer(PlayerLikeModel.MODEL))));
    }

    @Override
    public ResourceLocation getTextureLocation(StormTrooper entity) {
        if (entity.isLeader())
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/trooper_commander.png");

        else return new ResourceLocation(Reference.MOD_ID, "textures/entity/trooper_normal.png");
    }
}