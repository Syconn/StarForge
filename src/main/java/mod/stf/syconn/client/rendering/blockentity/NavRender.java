package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.client.rendering.entity.BlockRender;
import mod.stf.syconn.common.blockEntity.NavBE;
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
public class NavRender implements BlockEntityRenderer<NavBE> {

    Minecraft mc = Minecraft.getInstance();

    public NavRender(BlockEntityRendererProvider.Context pContext) {

    }

    @Override
    public void render(NavBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        System.out.println("RENDERING");
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.NAV_COMPUTER.get() && !pBlockEntity.getImages().isEmpty()) {
            BlockPos anchorPos = pBlockEntity.getImages().entrySet().iterator().next().getKey();
            for (Map.Entry<BlockPos, ServerPixelImage> map : pBlockEntity.getImages().entrySet()){
                BlockPos pos = map.getKey();
                pPoseStack.pushPose();
                BlockRender br = new BlockRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getResourceManager(), mc.getEntityModels(), mc.font), pBlockEntity, pos);

                AttachFace face = mc.level.getBlockState(pBlockEntity.getBlockPos()).getValue(BlockStateProperties.ATTACH_FACE);
                pPoseStack.translate(0, 0.2, 0);

                if (face == AttachFace.FLOOR) {
                    pPoseStack.translate(0.5, 0.3, 0.5);
                } else if (face == AttachFace.CEILING) {
                    pPoseStack.translate(0.5, -0.3, 0.5);
                } else if (face == AttachFace.WALL) {
                    pPoseStack.translate(0.5, 0.3, 0.5);
                    pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
                }

                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(180));
                pPoseStack.scale(0.2f, 0.2f, 0.2f);
                pPoseStack.translate(0, -4, 0);

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
