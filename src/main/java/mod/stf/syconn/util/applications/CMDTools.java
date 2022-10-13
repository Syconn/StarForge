package mod.stf.syconn.util.applications;

import mod.stf.syconn.api.util.Mths;
import net.minecraft.core.BlockPos;

public class CMDTools {

    public static BlockPos getBlockPos(String[] parameters){
        if (Mths.isNumeric(parameters[0]) && Mths.isNumeric(parameters[1]) && Mths.isNumeric(parameters[2])){
            return new BlockPos(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
        }

        return null;
    }
}
