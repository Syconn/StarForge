package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.client.rendering.entity.BlockRender;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SchematicRender implements BlockEntityRenderer<SchematicBe> {

    private final Minecraft mc = Minecraft.getInstance();

    public SchematicRender(BlockEntityRendererProvider.Context pContext) { }

    public void render(SchematicBe pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.SCHEMATIC_PROJECTOR.get() && !pBlockEntity.getImages().isEmpty()) {
            BlockPos anchorPos = pBlockEntity.getImages().entrySet().iterator().next().getKey();
            for (Map.Entry<BlockPos, PixelImage> map : pBlockEntity.getImages().entrySet()){
                BlockPos pos = map.getKey();
                pPoseStack.pushPose();
                BlockRender br = new BlockRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getBlockRenderer(), mc.gameRenderer.itemInHandRenderer, mc.getResourceManager(), mc.getEntityModels(), mc.font), pBlockEntity, pos);

                AttachFace face = mc.level.getBlockState(pBlockEntity.getBlockPos()).getValue(BlockStateProperties.ATTACH_FACE);
                pPoseStack.translate(0, 0.2, 0);

                if (face == AttachFace.FLOOR) {
                    pPoseStack.translate(0.5, 0.3, 0.5);
                } else if (face == AttachFace.CEILING) {
                    pPoseStack.translate(0.5, -0.3, 0.5);
                } else if (face == AttachFace.WALL) {
                    pPoseStack.translate(0.5, 0.3, 0.5);
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
                }

                pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
                pPoseStack.translate(0, -2, 0);

                if (anchorPos != pos){
                    double xDif = anchorPos.getX() - pos.getX();
                    double yDif = anchorPos.getY() - pos.getY();
                    double zDif = anchorPos.getZ() - pos.getZ();
                    pPoseStack.translate(xDif, yDif, zDif);
                }

                br.render(pPoseStack, pBufferSource, pPackedLight);
                pPoseStack.popPose();
            }
        }
    }
}
