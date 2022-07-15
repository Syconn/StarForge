package mod.stf.syconn.api.util;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.swing.text.html.parser.Entity;

public class Mths {

    public static Vec3 frontPos(LivingEntity entity, double increment){
        float f2 = Mth.sin(entity.yBodyRot * ((float)Math.PI / 180F));
        float f = Mth.cos(entity.yBodyRot * ((float)Math.PI / 180F));
        float f3 = Mth.sin(entity.getXRot() * ((float)Math.PI / 180F));

        System.out.println(f2);

        if (f2 < 0){
            return new Vec3(entity.getX() + (f2 + 0.3f), entity.getY() + f3, entity.getZ() - f);
        } else {
            return new Vec3(entity.getX() + f2, entity.getY() + f3, entity.getZ() - (f + 0.3f));
        }
    }

    public static double flipValue(double value, double check){
        if (check != 0){
            return -value;
        } else return value;
    }
}
