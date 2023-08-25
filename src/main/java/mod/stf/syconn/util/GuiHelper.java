package mod.stf.syconn.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;

public class GuiHelper {

    public static void fillRect(PoseStack pPoseStack, int pMinX, int pMinY, int pWidth, int pHeight, int pRed, int pGreen, int pBlue, int pAlpha) {
        int pMaxX = pMinX + pWidth;
        int pMaxY = pMinY + pHeight;
        Matrix4f matrix4f = pPoseStack.last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(matrix4f, (float)pMinX, (float)pMinY, (float)0).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pMinX, (float)pMaxY, (float)0).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pMaxX, (float)pMaxY, (float)0).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        bufferbuilder.vertex(matrix4f, (float)pMaxX, (float)pMinY, (float)0).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
    }
}
