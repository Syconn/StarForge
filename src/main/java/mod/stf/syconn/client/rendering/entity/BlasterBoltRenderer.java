package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.BoltModel;
import mod.stf.syconn.client.rendering.model.TestModel;
import mod.stf.syconn.common.entity.BlasterBoltEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BlasterBoltRenderer extends EntityRenderer<BlasterBoltEntity> {

    private BoltModel model;
    private ItemRenderer itemRenderer;

    public BlasterBoltRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        itemRenderer = ctx.getItemRenderer();
        model = new BoltModel(ctx.bakeLayer(TestModel.LAYER_LOCATION));
    }

    public void render(BlasterBoltEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, (double)0.15F, 0.0D);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
        this.model.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(getTextureLocation(pEntity)));
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BlasterBoltEntity pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/item/red_lightsaber.png");
    }
}
