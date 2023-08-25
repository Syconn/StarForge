package mod.stf.syconn.item.lightsaber;

import net.minecraft.world.item.DyeColor;

public class LightsaberData {

    private HandleType handle;
    private boolean state;
    private LColor color;

    private boolean hideBar = false;

    public LightsaberData(HandleType handle, boolean state, LColor color) {
        this.handle = handle;
        this.state = state;
        this.color = color;
    }

    public LightsaberData(HandleType handle, boolean state, LColor color, boolean hideBar) {
        this.handle = handle;
        this.state = state;
        this.color = color;
        this.hideBar = hideBar;
    }

    public HandleType getHandle() {
        return handle;
    }

    public boolean isActive() {
        return state;
    }

    public LColor getColor() {
        return color;
    }

    public boolean hideBar() {
        return hideBar;
    }

    public LightsaberData setHandle(HandleType handle) {
        this.handle = handle;
        return this;
    }

    public LightsaberData setState(boolean state) {
        this.state = state;
        return this;
    }

    public LightsaberData setColor(LColor color) {
        this.color = color;
        return this;
    }

    public enum HandleType {
        AHSOKA("ahsoka", "Ahsoka Tano's", 1, DyeColor.WHITE),
        ANAKIN("anakin", "Anakin Skywalker's", 2, DyeColor.BLUE),
        DARK_SABER("dark_saber", "Dark Saber", 3, DyeColor.BLACK),
        GUARD("guard", "Temple Guard's", 4, DyeColor.YELLOW),
        KAL("kal", "Kal Kestis's", 5, DyeColor.BLUE),
        KYLO("kylo", "Kylo Ren's", 6, DyeColor.RED),
        LUKE("luke", "Luke Skywalker's", 7, DyeColor.LIME),
        MACE("mace", "Mace Windu's", 8, DyeColor.PURPLE),
        MAUL("maul", "Darth Maul's", 9, DyeColor.RED),
        OBI("obi", "Kenobi's", 10, DyeColor.BLUE),
        YODA("yoda", "Yoda's", 11, DyeColor.LIME);

        private final String type;
        private final String name;
        private final int id;
        private final DyeColor defaultColor;

        HandleType(String type, String name, int id, DyeColor defaultColor) {
            this.type = type;
            this.id = id;
            this.name = name;
            this.defaultColor = defaultColor;
        }

        public String getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public DyeColor getDefaultColor() {
            return defaultColor;
        }

        public int getColor(){
            return defaultColor.getFireworkColor();
        }

        public static HandleType getHandle(float id){
            for (HandleType type : HandleType.values()){
                if (type.id == id) return type;
            }
            return null;
        }
    }
}
