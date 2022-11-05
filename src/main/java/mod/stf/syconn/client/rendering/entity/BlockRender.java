package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.blockEntity.MenuBlockEntity;
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

    public BlockRender(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(MovingBlock pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(-0.5, 0, -0.5);
        if (pEntity.getBlockState() != null) {
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(pEntity.getBlockState(), pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY);
        }
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    public void render(BlockState state, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        //pPoseStack.translate(-0.5, 0, -0.5);
        if (state != null) {
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY);
        }
        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(MovingBlock pEntity) {
//        try {
//            return Minecraft.getInstance().getTextureManager().register("moving_block", new DynamicTexture(pEntity.getTexture().getImageFromPixels()));
//        } catch (IOException e) {
//            return new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png");
//        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png");
    }
}
