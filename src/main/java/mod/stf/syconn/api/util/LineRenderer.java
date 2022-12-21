package mod.stf.syconn.api.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.util.TripPath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.ChunkBorderRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.event.RenderLevelLastEvent;

import java.util.List;

public class LineRenderer {

    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void render(BlockPos x, BlockPos z, LColor c) {
        double pCamX = minecraft.cameraEntity.getX();
        double pCamY = minecraft.cameraEntity.getY();
        double pCamZ = minecraft.cameraEntity.getZ();
        RenderSystem.enableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.disableTexture();
        RenderSystem.disableBlend();
        RenderSystem.lineWidth(2.0F);
        bufferbuilder.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        bufferbuilder.vertex(x.getX() - pCamX, x.getY() - pCamY, x.getZ() - pCamZ).color(c.getRGBA()).endVertex();
        bufferbuilder.vertex(z.getX() - pCamX, z.getY() - pCamY, z.getZ() - pCamZ).color(c.getRGBA()).endVertex();

        tesselator.end();
        RenderSystem.lineWidth(2.0F);
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
    }

    public static void renderTripPath(TripPath path, PoseStack s){
        double d0 = minecraft.cameraEntity.getX();
        double d1 = minecraft.cameraEntity.getY();
        double d2 = minecraft.cameraEntity.getZ();
        //s.translate((double)path.getPoint(0).getX() - d0, (double)path.getPoint(0).getY() - d1, (double)path.getPoint(0).getZ() - d2);
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.mulPoseMatrix(s.last().pose());
        RenderSystem.applyModelViewMatrix();
        render(path.getPoint(0), path.getPoint(0), LColor.of(DyeColor.BLACK));
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
    }
}
