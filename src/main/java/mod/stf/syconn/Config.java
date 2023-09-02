package mod.stf.syconn;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static final ForgeConfigSpec COMMON;
    public static ForgeConfigSpec.IntValue minYRenderHeight;
    public static ForgeConfigSpec.BooleanValue betterFPSRendering;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        COMMON = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Map Hologram Rendering Settings");
        minYRenderHeight = builder.defineInRange("lowest_ypos_cap", 50, Integer.MIN_VALUE, Integer.MAX_VALUE);
        betterFPSRendering = builder.define("better_fps_rendering", false);
    }
}
