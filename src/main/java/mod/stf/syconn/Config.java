package mod.stf.syconn;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Common
    {
        /** Config.COMMON.goblinTrader.canAttackBack.get(); */

        public final ItemDrone drones;

        Common(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Common configuration settings").push("common");
            this.drones = new ItemDrone(builder, "ItemDrone", "item_drone");
            builder.pop();
        }

        public static class ItemDrone
        {
            public final ForgeConfigSpec.IntValue maxChestRange;
            public final ForgeConfigSpec.BooleanValue capItemLimit;
            public final ForgeConfigSpec.IntValue ItemLimit;
            public final ForgeConfigSpec.IntValue movementSpeed;
            public final ForgeConfigSpec.IntValue pickupItemSpeed;

            ItemDrone(ForgeConfigSpec.Builder builder, String name, String key)
            {
                builder.comment(name + " settings").push(key);
                this.maxChestRange = builder
                        .comment("The max range to find chest")
                        .defineInRange("maxChestRange", 20, 10, 100);
                this.capItemLimit = builder
                        .comment("The amount of different types of items drone can show")
                        .define("capItemLimit", true);
                this.ItemLimit = builder
                        .comment("Limits Diffrent Items amount only if capItemLimit is true")
                        .defineInRange("ItemLimit", 27, 0, 27);
                this.movementSpeed = builder
                        .comment("speed of drone when getting items")
                        .defineInRange("movementSpeed", 10, 0, 256);
                this.pickupItemSpeed = builder
                        .comment("speed of tacking items from chests")
                        .defineInRange("pickupItemSpeed", 25, 0, 256);
                builder.pop();
            }
        }
    }

    static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static
    {
        final Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonPair.getRight();
        COMMON = commonPair.getLeft();
    }
}
