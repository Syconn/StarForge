package mod.stf.syconn.api.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class Mths {

    public static Vec3 frontPos(LivingEntity entity, double increment){
        float f2 = Mth.sin(entity.yBodyRot * ((float)Math.PI / 180F));
        float f = Mth.cos(entity.yBodyRot * ((float)Math.PI / 180F));
        float f3 = Mth.sin(entity.getXRot() * ((float)Math.PI / 180F));

        float f2w = Mth.wrapDegrees(f2) * 100;
        float fw = Mth.wrapDegrees(f) * 100;
        float f3w = Mth.wrapDegrees(f3) * 100;

        System.out.println(f2w);

        if (f2 < 0){
            return new Vec3(entity.getX() + (f2 + increment), entity.getY() + f3, entity.getZ() - f);
        } else {
            return new Vec3(entity.getX() + f2, entity.getY() + f3, entity.getZ() - (f + increment));
        }
    }

    public static int flip(double value){
        if (value > 0)
            return -1;
        else if (value < 0)
            return 1;
        else return 0;
    }

    public static int mode(final int[] n) {
        int r = 0;
        int max = 0;
        for (int k : n) {
            int cur = 0;
            for (int j : n) if (k == j) cur++;
            if (cur > max) {
                r = k;
                max = cur;
            }
        }
        return r;
    }
}
