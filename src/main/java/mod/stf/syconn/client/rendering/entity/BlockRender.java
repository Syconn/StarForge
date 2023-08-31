package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.BlockModel;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BlockRender extends EntityRenderer<Entity> {

    private final BlockModel model;
    private final SchematicBe be;
    private final BlockPos pos;

    public BlockRender(EntityRendererProvider.Context pContext, SchematicBe be, BlockPos pos) {
        super(pContext);
        this.be = be;
        this.pos = pos;
        this.model = new BlockModel(pContext.bakeLayer(BlockModel.LAYER_LOCATION));
    }

    public void render(Entity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        model.setupAnim(pEntity, pPartialTick, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(getTextureLocation(pEntity)));
        model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(getTexture()));
        model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }

    public ResourceLocation getTexture(){
        return Minecraft.getInstance().getTextureManager().register("holo", this.be.getBlockImage(pos));
    }

    public ResourceLocation getTextureLocation(Entity pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png");
    }
}
