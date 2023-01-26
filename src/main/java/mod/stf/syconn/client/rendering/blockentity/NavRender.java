package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.client.rendering.entity.BlockRender;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.util.TripPath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NavRender implements BlockEntityRenderer<NavBE> {

    Minecraft mc = Minecraft.getInstance();

    public NavRender(BlockEntityRendererProvider.Context pContext) {

    }

    @Override
    public void render(NavBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.NAV_COMPUTER.get()) {
            TripPath path = pBlockEntity.getPath();
            if (pBlockEntity.shouldShipRender()) {
                for (BlockID id : pBlockEntity.getShip().getBlockIDs()){
                    if (pBlockEntity.getPosition(id.pos()) != null) {
                        pPoseStack.pushPose();
                        BlockState bs = id.state();
                        double[] position = pBlockEntity.getPosition(id.pos());
                        BlockRender br = new BlockRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getResourceManager(), mc.getEntityModels(), mc.font));
                        pPoseStack.translate(0, 0.2, 0);
                        pPoseStack.translate(0.5, 0.3, 0.5);
                        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(180));
                        pPoseStack.translate(position[0], position[1], position[2]);
                        pPoseStack.scale(0.2f, 0.2f, 0.2f);
                        pPoseStack.translate(position[3] - 0.5, position[4] - 3.5, position[5] - 0.5);
                        br.render(bs, pPoseStack, pBufferSource, pPackedLight);
                        pPoseStack.popPose();
                    }
                }
            }
            if (path != null && pBlockEntity.showPath()){
                BlockPos lastPos = pBlockEntity.getBlockPos();
                for (int i = 0; i < path.getTotalPoints(); i++){
                    TripPath.Directions d = path.getDirections(i);
                    BlockState state = d.pos() == path.getTarget() ? Blocks.GOLD_BLOCK.defaultBlockState() : Blocks.IRON_BLOCK.defaultBlockState();
                    int x = lastPos.getX() - d.pos().getX();
                    int y = lastPos.getY() - d.pos().getY();
                    int z = lastPos.getZ() - d.pos().getZ();
                    pPoseStack.translate(-x, -y, -z);
                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
                    lastPos = d.pos();
                }
            }
        }
    }

    public int getViewDistance() {
        return 256;
    }

    public boolean shouldRender(NavBE pBlockEntity, Vec3 pCameraPos) {
        return Vec3.atCenterOf(pBlockEntity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D).closerThan(pCameraPos.multiply(1.0D, 0.0D, 1.0D), (double)this.getViewDistance());
    }

    @Override
    public boolean shouldRenderOffScreen(NavBE pBlockEntity) {
        return true;
    }
}
