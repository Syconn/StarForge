package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.rendering.model.BlockModel;
import mod.stf.syconn.client.rendering.model.TieModel;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import mod.stf.syconn.common.entity.MovingBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;

public class BlockRender extends EntityRenderer<MovingBlock> {

    private BlockModel model;
    private SchematicBe be;
    private BlockPos pos;

    public BlockRender(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    public BlockRender(EntityRendererProvider.Context pContext, SchematicBe be, BlockPos pos) {
        super(pContext);
        this.be = be;
        this.pos = pos;
        this.model = new BlockModel(pContext.bakeLayer(BlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(MovingBlock pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
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
        return Minecraft.getInstance().getTextureManager().register("holo", new DynamicTexture(this.be.getBlockImage(pos)));
    }

    @Override
    public ResourceLocation getTextureLocation(MovingBlock pEntity) {
        try {
            return Minecraft.getInstance().getTextureManager().register("moving_block", new DynamicTexture(pEntity.getTexture().getImageFromPixels()));
        } catch (IOException e) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png");
        }
    }
}
