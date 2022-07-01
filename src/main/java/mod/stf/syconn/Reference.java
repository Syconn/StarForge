package mod.stf.syconn;

import net.minecraft.resources.ResourceLocation;

public class Reference {

    public static final String MOD_ID = "stf";
    public static final Boolean DEV_MODE = true;

    public static ResourceLocation loc(String loc){
        return new ResourceLocation(MOD_ID, loc);
    }
}
