package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.client.rendering.entity.BlockRender;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SchematicRender implements BlockEntityRenderer<SchematicBe> {

    Minecraft mc = Minecraft.getInstance();

    public SchematicRender(BlockEntityRendererProvider.Context pContext) {
    }

    @Override
    public void render(SchematicBe pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.SCHEMATIC_PROJECTOR.get() && pBlockEntity.getSchematic() != null) {
            for (BlockID id : pBlockEntity.getSchematic().getBlockIDs()) {
                BlockState state = id.state();
                double[] position = pBlockEntity.getPosition(id.pos());
                pPoseStack.pushPose();
                BlockRender br = new BlockRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getResourceManager(), mc.getEntityModels(), mc.font));
                pPoseStack.translate(position[0], position[1], position[2]);
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(180));
                pPoseStack.translate(0, -2, -1);
                br.render(state, pPoseStack, pBufferSource, pPackedLight);
                pPoseStack.popPose();
            }
        }
    }
}
