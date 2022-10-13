package mod.stf.syconn.api.util;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class Mths {

    public static String[] splitArray(String[] array, int start, int length){
        String[] list = new String[length];
        list[0] = array[start];
        list[1] = array[start + 1];
        list[2] = array[start + 2];
        return list;
    }

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

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public static int largestSum(int... values){
        int biggest = 0;
        for (int value : values){
            if (biggest < value)
                biggest = value;
        }
        return biggest;
    }
}
