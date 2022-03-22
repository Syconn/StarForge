package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mod.stf.syconn.common.entity.LightsaberEntity;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LightsaberRenderer extends EntityRenderer<LightsaberEntity> {

    private ItemRenderer itemRenderer;

    public LightsaberRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(LightsaberEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, (double)0.15F, 0.0D);
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(pEntity.spin));
        itemRenderer.render(pEntity.getItem(), ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, 15728880, OverlayTexture.NO_OVERLAY, itemRenderer.getModel(pEntity.getItem(), null, Minecraft.getInstance().player, 0));
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(LightsaberEntity pEntity) {
        return null;
    }
}
