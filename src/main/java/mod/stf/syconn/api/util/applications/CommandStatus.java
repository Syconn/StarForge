package mod.stf.syconn.api.util.applications;

import mod.stf.syconn.api.util.ColorFormattedLine;
import mod.stf.syconn.api.util.ColoredString;
import net.minecraft.world.item.DyeColor;

public record CommandStatus(String msg, Status status) {

    public ColoredString getStatus(){
        return new ColoredString(status.type + " - " + msg, status.color);
    }

    public enum Status {
        SUCCESS("SUCCESS", DyeColor.LIME.getFireworkColor()),
        HELP("USAGE", DyeColor.BLUE.getFireworkColor()),
        WARN("WARN", DyeColor.YELLOW.getFireworkColor()),
        UNKNOWN("INVALID", DyeColor.PURPLE.getFireworkColor()),
        ERROR("ERROR", DyeColor.RED.getFireworkColor());

        private String type;
        private int color;

        Status(String type, int color) {
            this.type = type;
            this.color = color;
        }

        public boolean isSuccessful(){
            return type.matches(SUCCESS.getType());
        }

        public String getType() {
            return type;
        }

        public int getColor() {
            return color;
        }
    }
}
