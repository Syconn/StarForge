package mod.stf.syconn.api.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mod.stf.syconn.api.util.data.VectorData;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.client.model.data.ModelData;

import java.util.ArrayList;
import java.util.List;

public class RenderUtil {

    public static void renderSingleBlock(BlockState pState, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, BlockAndTintGetter level, BlockPos pos, VectorData data) {
        RenderShape rendershape = pState.getRenderShape();
        if (rendershape != RenderShape.INVISIBLE) {
            switch (rendershape) {
                case MODEL -> {
                    BakedModel bakedmodel = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(pState);
                    // TODO Fix below Water
                    for (RenderType rt : bakedmodel.getRenderTypes(pState, RandomSource.create(42), ModelData.EMPTY)) {
                        RandomSource randomsource = RandomSource.create();
                        for(Direction direction : handleHiddenFaces(data.getBlockFaces())) {
                            randomsource.setSeed(42L);
                            renderQuadList(pPoseStack.last(), pBufferSource.getBuffer(RenderTypeHelper.getEntityRenderType(rt, false)), data.getRGB()[0], data.getRGB()[1], data.getRGB()[2], bakedmodel.getQuads(pState, direction, randomsource, ModelData.EMPTY, rt), pPackedLight);
                        }
                        randomsource.setSeed(42L);
                        renderQuadList(pPoseStack.last(), pBufferSource.getBuffer(RenderTypeHelper.getEntityRenderType(rt, false)), data.getRGB()[0], data.getRGB()[1], data.getRGB()[2], bakedmodel.getQuads(pState, null, randomsource, ModelData.EMPTY, rt), pPackedLight);
                    }
                }
                case ENTITYBLOCK_ANIMATED -> IClientItemExtensions.of(new ItemStack(pState.getBlock())).getCustomRenderer().renderByItem(new ItemStack(pState.getBlock()), ItemDisplayContext.NONE, pPoseStack, pBufferSource, pPackedLight, OverlayTexture.NO_OVERLAY);
            }
        }
    }

    private static List<Direction> handleHiddenFaces(List<Direction> input) {
        List<Direction> list = new ArrayList<>(input);
        if (Minecraft.getInstance().options.getCameraType() == CameraType.THIRD_PERSON_FRONT) list.remove(Minecraft.getInstance().player.getDirection().getOpposite());
        else list.remove(Minecraft.getInstance().player.getDirection());
        return list;
    }

    private static void renderQuadList(PoseStack.Pose pPose, VertexConsumer pConsumer, float pRed, float pGreen, float pBlue, List<BakedQuad> pQuads, int pPackedLight) {
        for(BakedQuad bakedquad : pQuads) {
            float f;
            float f1;
            float f2;
            if (bakedquad.isTinted()) {
                f = Mth.clamp(pRed, 0.0F, 1.0F);
                f1 = Mth.clamp(pGreen, 0.0F, 1.0F);
                f2 = Mth.clamp(pBlue, 0.0F, 1.0F);
            } else {
                f = 1.0F;
                f1 = 1.0F;
                f2 = 1.0F;
            }
            pConsumer.putBulkData(pPose, bakedquad, f, f1, f2, pPackedLight, OverlayTexture.NO_OVERLAY);
        }
    }
    
    public static void renderLiquid(PoseStack pPoseStack, MultiBufferSource pBufferSource, BlockState fluid, VectorData data) {
        if (!fluid.getFluidState().isEmpty()) {
            ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid.getFluidState()).getStillTexture();
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
            VertexConsumer builder = pBufferSource.getBuffer(RenderType.translucent());
            float a = 1.0f;
            float r = data.getRGB()[0];
            float g = data.getRGB()[1];
            float b = data.getRGB()[2];

            pPoseStack.pushPose();
            // Top Face
            if (data.getVectorData(Direction.UP)) {
                add(builder, pPoseStack, 0, data.getBlockHeight(), 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 1, data.getBlockHeight(), 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 1, data.getBlockHeight(), 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 0, data.getBlockHeight(), 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
            }

            // Front Faces [NORTH - SOUTH]
            if (data.getVectorData(Direction.SOUTH)) {
                add(builder, pPoseStack, 1, data.getBlockHeight(), 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 0, data.getBlockHeight(), 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
            }
            if (data.getVectorData(Direction.NORTH)) {
                add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 0, data.getBlockHeight(), 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 1, data.getBlockHeight(), 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
            }

            pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
            pPoseStack.translate(-1f, 0, 0);

            // Front Faces [EAST - WEST]
            if (data.getVectorData(Direction.EAST)) {
                add(builder, pPoseStack, 1, data.getBlockHeight(), 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 0, data.getBlockHeight(), 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
            }
            if (data.getVectorData(Direction.WEST)) {
                add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
                add(builder, pPoseStack, 0, data.getBlockHeight(), 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
                add(builder, pPoseStack, 1, data.getBlockHeight(), 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
            }

            //            // Bottom Face of Top
//            add(builder, pPoseStack, 1, data.getBlockHeight(), 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, data.getBlockHeight(), 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, data.getBlockHeight(), 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 1, data.getBlockHeight(), 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);

//            // Back Faces
//            add(builder, pPoseStack, 1, data.getBlockHeight(), 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 0, data.getBlockHeight(), 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);
//
//            add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
//            add(builder, pPoseStack, 0, data.getBlockHeight(), 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
//            add(builder, pPoseStack, 1, data.getBlockHeight(), 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
            pPoseStack.popPose();
        }
    }

    private static void add(VertexConsumer renderer, PoseStack stack, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        renderer.vertex(stack.last().pose(), x, y, z).color(r, g, b, a).uv(u, v).uv2(0, 240).normal(1, 0, 0).endVertex();
    }
}
