package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.common.blockEntity.MapBe;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.util.data.BlockInChunkData;
import mod.stf.syconn.util.data.ChunkData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MapRender implements BlockEntityRenderer<MapBe> {

    private final float Scale3x3 = 0.063f;

    private final Minecraft mc = Minecraft.getInstance();

    public MapRender(BlockEntityRendererProvider.Context pContext) { }

    public void render(MapBe pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.MAP_PROJECTOR.get()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0, 0.45, 0);
            pPoseStack.scale(Scale3x3, Scale3x3, Scale3x3);
            for (ChunkData data : pBlockEntity.getChunk()) {
                pPoseStack.pushPose();
                pPoseStack.translate(data.getX() * 16, 0, data.getZ() * 16);
                for (BlockInChunkData blockInChunkData : data.getBlocks()) blockInChunkData.render(pPoseStack, pBufferSource, pPackedLight, data.getChunk(), pBlockEntity.getLowestY());
                pPoseStack.popPose();
            }
            pPoseStack.popPose();
        }
    }

    public boolean shouldRenderOffScreen(MapBe pBlockEntity) {
        return true;
    }

    public int getViewDistance() {
        return 32;
    }

    public boolean shouldRender(MapBe pBlockEntity, Vec3 pCameraPos) {
        return Vec3.atCenterOf(pBlockEntity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D).closerThan(pCameraPos.multiply(1.0D, 0.0D, 1.0D), (double)this.getViewDistance());
    }
}
