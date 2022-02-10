package mod.stf.syconn.client.utils;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;

public class BakedQuadUtils {

    public static void offsetQuadInDir(BakedQuad quad, Direction dir, float amount)
    {
        int idx = dir.getAxis().ordinal();
        float value = dir.getAxisDirection() == Direction.AxisDirection.POSITIVE ? amount : (-1F * amount);
        ModelUtils.modifyQuad(quad, (pos, color, uv, light, normal) ->
        {
            for (int i = 0; i < 4; i++)
            {
                pos[i][idx] += value;
            }
            return true;
        });
    }
}
