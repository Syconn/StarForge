package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.TieModel;
import mod.stf.syconn.common.entity.TieFighter;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Rotation;

public class TieFighterRenderer extends EntityRenderer<TieFighter> {

    private final TieModel model;

    public TieFighterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new TieModel(pContext.bakeLayer(TieModel.LAYER_LOCATION));
    }

    @Override
    public void render(TieFighter pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0, 1.5, 0);
        //pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - pEntityYaw));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        pPoseStack.mulPose(Vector3f.XN.rotationDegrees(Mth.wrapDegrees(pEntity.getXRot())));
        model.setupAnim(pEntity, pPartialTick, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(getTextureLocation(pEntity)));
        model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(TieFighter pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/tie.png");
    }
}
