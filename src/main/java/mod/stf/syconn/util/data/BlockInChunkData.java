package mod.stf.syconn.util.data;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.util.RenderUtil;
import mod.stf.syconn.api.util.data.VectorData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

public record BlockInChunkData(BlockState state, BlockPos pos, int x, int z) {

    public void render(PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, LevelChunk level, int lowestY) {
        pPoseStack.pushPose();
        pPoseStack.translate(x, pos.getY() - lowestY, z);
        if (state.getFluidState().isEmpty()) Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, pPoseStack, pBufferSource, pPackedLight, OverlayTexture.NO_OVERLAY);
        else RenderUtil.renderLiquid(pPoseStack, pBufferSource, state.getFluidState(), new VectorData(level.getLevel(), pos, level));
        pPoseStack.popPose();
    }
}
