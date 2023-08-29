package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mod.stf.syconn.common.entity.ThrownLightsaber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;

public class LightsaberRenderer extends EntityRenderer<ThrownLightsaber> {

    private ItemRenderer itemRenderer;

    public LightsaberRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        itemRenderer = ctx.getItemRenderer();
    }

    public void render(ThrownLightsaber pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, (double)0.15F, 0.0D);
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(pEntity.spin));
        itemRenderer.render(pEntity.getItem(), ItemDisplayContext.GROUND, false, pMatrixStack, pBuffer, 15728880, OverlayTexture.NO_OVERLAY, itemRenderer.getModel(pEntity.getItem(), null, Minecraft.getInstance().player, 0));
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    public ResourceLocation getTextureLocation(ThrownLightsaber pEntity) {
        return null;
    }
}
