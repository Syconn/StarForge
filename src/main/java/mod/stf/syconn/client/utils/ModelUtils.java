package mod.stf.syconn.client.utils;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraftforge.client.model.pipeline.LightUtil;

public class ModelUtils {

    public static boolean modifyQuad(BakedQuad quad, VertexDataConsumer consumer)
    {
        int elemPos = findElement(VertexFormatElement.Usage.POSITION, 0);
        int elemColor = findElement(VertexFormatElement.Usage.COLOR, 0);
        int elemUV = findElement(VertexFormatElement.Usage.UV, 0);
        int elemLight = findElement(VertexFormatElement.Usage.UV, 2);
        int elemNormal = findElement(VertexFormatElement.Usage.NORMAL, 0);

        int[] vertexData = quad.getVertices();

        float[][] pos = new float[4][3];
        float[][] color = new float[4][4];
        float[][] uv = new float[4][2];
        float[][] light = new float[4][2];
        float[][] normal = new float[4][3];

        for (int vert = 0; vert < 4; vert++)
        {
            LightUtil.unpack(vertexData, pos[vert], DefaultVertexFormat.BLOCK, vert, elemPos);
            LightUtil.unpack(vertexData, color[vert], DefaultVertexFormat.BLOCK, vert, elemColor);
            LightUtil.unpack(vertexData, uv[vert], DefaultVertexFormat.BLOCK, vert, elemUV);
            LightUtil.unpack(vertexData, light[vert], DefaultVertexFormat.BLOCK, vert, elemLight);
            LightUtil.unpack(vertexData, normal[vert], DefaultVertexFormat.BLOCK, vert, elemNormal);
        }

        boolean success = consumer.accept(pos, color, uv, light, normal);
        if (!success) { return false; }

        for (int vert = 0; vert < 4; vert++)
        {
            LightUtil.pack(pos[vert], vertexData, DefaultVertexFormat.BLOCK, vert, elemPos);
            LightUtil.pack(color[vert], vertexData, DefaultVertexFormat.BLOCK, vert, elemColor);
            LightUtil.pack(uv[vert], vertexData, DefaultVertexFormat.BLOCK, vert, elemUV);
            LightUtil.pack(light[vert], vertexData, DefaultVertexFormat.BLOCK, vert, elemLight);
            LightUtil.pack(normal[vert], vertexData, DefaultVertexFormat.BLOCK, vert, elemNormal);
        }

        fillNormal(quad);

        return true;
    }

    /**
     * Calculate face normals from vertex positions
     */
    public static void fillNormal(BakedQuad quad)
    {
        float[][] pos = unpackElement(quad, VertexFormatElement.Usage.POSITION, 0);

        Vector3f v1 = new Vector3f(pos[3][0], pos[3][1], pos[3][2]);
        Vector3f t1 = new Vector3f(pos[1][0], pos[1][1], pos[1][2]);
        Vector3f v2 = new Vector3f(pos[2][0], pos[2][1], pos[2][2]);
        Vector3f t2 = new Vector3f(pos[0][0], pos[0][1], pos[0][2]);

        v1.sub(t1);
        v2.sub(t2);
        v2.cross(v1);
        v2.normalize();

        int x = ((byte) Math.round(v2.x() * 127)) & 0xFF;
        int y = ((byte) Math.round(v2.y() * 127)) & 0xFF;
        int z = ((byte) Math.round(v2.z() * 127)) & 0xFF;

        int normal = x | (y << 0x08) | (z << 0x10);

        int[] vertexData = quad.getVertices();
        int step = vertexData.length / 4; //This is needed to support the extended vertex formats used by shaders in OptiFine
        for (int vert = 0; vert < 4; vert++)
        {
            vertexData[vert * step + 7] = normal;
        }
    }

    public static float[][] unpackElement(BakedQuad quad, VertexFormatElement.Usage usage, int index)
    {
        int elemPos = findElement(usage, index);

        float[][] data = new float[4][4];
        for (int vert = 0; vert < 4; vert++)
        {
            LightUtil.unpack(quad.getVertices(), data[vert], DefaultVertexFormat.BLOCK, vert, elemPos);
        }
        return data;
    }

    public static int findElement(VertexFormatElement.Usage usage, int index)
    {
        int idx = 0;
        for (VertexFormatElement element : DefaultVertexFormat.BLOCK.getElements())
        {
            if (element.getUsage() == usage && element.getIndex() == index)
            {
                return idx;
            }
            idx++;
        }
        throw new IllegalArgumentException("Format doesn't have a " + usage.getName() + " element");
    }

    public interface VertexDataConsumer
    {
        boolean accept(float[][] pos, float[][] color, float[][] uv, float[][] light, float[][] normal);
    }
}
