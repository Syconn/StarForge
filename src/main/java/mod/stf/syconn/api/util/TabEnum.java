package mod.stf.syconn.api.util;

public enum TabEnum {
    S1(1),
    S2(2),
    S3(3),
    S4(4),
    S5(5),
    S6(6),
    S7(7),
    S8(8);

    private int value;

    TabEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TabEnum getById(int i) {
        for (TabEnum tab : values()){
            if (tab.value == i)
                return tab;
        }
        return null;
    }
}
