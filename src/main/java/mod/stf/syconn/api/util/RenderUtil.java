package mod.stf.syconn.api.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mod.stf.syconn.api.util.data.VectorData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.lighting.ForgeModelBlockRenderer;

public class RenderUtil {

    public static void renderSingleBlock(BlockState pState, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, BlockAndTintGetter level, BlockPos pos) {
        RenderShape rendershape = pState.getRenderShape();
        if (rendershape != RenderShape.INVISIBLE) {
            switch (rendershape) {
                case MODEL -> {
                    BakedModel bakedmodel = getBlockModel(pState);
                    int i = Minecraft.getInstance().getBlockColors().getColor(pState, level, pos, 0);
                    float f = (float) (i >> 16 & 255) / 255.0F;
                    float f1 = (float) (i >> 8 & 255) / 255.0F;
                    float f2 = (float) (i & 255) / 255.0F;
                    for (RenderType rt : bakedmodel.getRenderTypes(pState, RandomSource.create(42), ModelData.EMPTY))
                        new ForgeModelBlockRenderer(Minecraft.getInstance().getBlockColors()).renderModel(pPoseStack.last(), pBufferSource.getBuffer(RenderTypeHelper.getEntityRenderType(rt, false)), pState, bakedmodel, f, f1, f2, pPackedLight, pPackedOverlay, ModelData.EMPTY, rt);
                }
                case ENTITYBLOCK_ANIMATED -> {
                    ItemStack stack = new ItemStack(pState.getBlock());
                    IClientItemExtensions.of(stack).getCustomRenderer().renderByItem(stack, ItemDisplayContext.NONE, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
                }
            }

        }
    }

    public static BakedModel getBlockModel(BlockState pState) {
        return Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(pState);
    }

    private static void add(VertexConsumer renderer, PoseStack stack, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        renderer.vertex(stack.last().pose(), x, y, z).color(r, g, b, a).uv(u, v).uv2(0, 240).normal(1, 0, 0).endVertex();
    }
    
    public static void renderLiquid(PoseStack pPoseStack, MultiBufferSource pBufferSource, FluidState fluid, VectorData data) {
        ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid).getStillTexture();
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);

        VertexConsumer builder = pBufferSource.getBuffer(RenderType.translucent());
        int color = IClientFluidTypeExtensions.of(fluid).getTintColor(fluid, data.getLevel(), data.getPos());
        float a = 1.0F;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;

        pPoseStack.pushPose();

        // Top Face
        add(builder, pPoseStack, 0, 1, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
        add(builder, pPoseStack, 1, 1, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
        add(builder, pPoseStack, 1, 1, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
        add(builder, pPoseStack, 0, 1, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);

        // Bottom Face
        add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
        add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
        add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
        add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);

        // Front Faces [NORTH - SOUTH]
        if (data.getVectorData(Direction.SOUTH)) {
            add(builder, pPoseStack, 1, 1, 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 0, 1, 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
        }
        if (data.getVectorData(Direction.NORTH)) {
            add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 0, 1, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 1, 1, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
        }

        pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
        pPoseStack.translate(-1f, 0, 0);

//      Front Faces [EAST - WEST]
        if (data.getVectorData(Direction.EAST)) {
            add(builder, pPoseStack, 1, 1, 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 0, 1, 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
        }
        if (data.getVectorData(Direction.WEST)) {
            add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
            add(builder, pPoseStack, 0, 1, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
            add(builder, pPoseStack, 1, 1, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
        }
//
//        Bottom Face of Top
//        add(builder, pPoseStack, 1, 1, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
//        add(builder, pPoseStack, 0, 1, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
//        add(builder, pPoseStack, 0, 1, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
//        add(builder, pPoseStack, 1, 1, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);

//      Back Faces
//        add(builder, pPoseStack, 1, 1, 0, sprite.getU0(), sprite.getV0(), r, g, b, a);
//        add(builder, pPoseStack, 0, 1, 0, sprite.getU1(), sprite.getV0(), r, g, b, a);
//        add(builder, pPoseStack, 0, 0, 0, sprite.getU1(), sprite.getV1(), r, g, b, a);
//        add(builder, pPoseStack, 1, 0, 0, sprite.getU0(), sprite.getV1(), r, g, b, a);

//        add(builder, pPoseStack, 1, 0, 1, sprite.getU0(), sprite.getV1(), r, g, b, a);
//        add(builder, pPoseStack, 0, 0, 1, sprite.getU1(), sprite.getV1(), r, g, b, a);
//        add(builder, pPoseStack, 0, 1, 1, sprite.getU1(), sprite.getV0(), r, g, b, a);
//        add(builder, pPoseStack, 1, 1, 1, sprite.getU0(), sprite.getV0(), r, g, b, a);
        pPoseStack.popPose();
    }
}
