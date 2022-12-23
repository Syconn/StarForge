package mod.stf.syconn.api.util;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
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

    /**
     * @param pos1 should be larger blockpos
     * @param pos2 should be smaller blockpos
     * @return center pos between pos1, pos2
     */
    public static BlockPos getCenter(BlockPos pos1, BlockPos pos2){
        return new BlockPos((pos1.getX() + pos2.getX()) / 2, (pos1.getY() + pos2.getY()) / 2, (pos1.getZ() + pos2.getZ()) / 2);
    }

    public static int distanceToPos(BlockPos p1, BlockPos p2){
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()) + Math.abs(p1.getZ() - p2.getZ());
    }

    public static Vec3 frontPos(LivingEntity entity, double increment){
        float f2 = Mth.sin(entity.yBodyRot * ((float)Math.PI / 180F));
        float f = Mth.cos(entity.yBodyRot * ((float)Math.PI / 180F));
        float f3 = Mth.sin(entity.getXRot() * ((float)Math.PI / 180F));

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
}
