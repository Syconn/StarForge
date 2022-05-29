package mod.stf.syconn.client.rendering.entity;

import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.StormTrooperModel;
import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class StormTrooperRender extends HumanoidMobRenderer<StormTrooper, StormTrooperModel> {

    public StormTrooperRender(EntityRendererProvider.Context context) {
        super(context, new StormTrooperModel(context.bakeLayer(StormTrooperModel.STORMTROOPER)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new StormTrooperModel(context.bakeLayer(StormTrooperModel.STORMTROOPER)), new StormTrooperModel(context.bakeLayer(StormTrooperModel.STORMTROOPER))));
    }

    @Override
    public ResourceLocation getTextureLocation(StormTrooper entity) {
        if (entity.isLeader())
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/trooper_commander.png");

        else return new ResourceLocation(Reference.MOD_ID, "textures/entity/trooper_normal.png");
    }
}