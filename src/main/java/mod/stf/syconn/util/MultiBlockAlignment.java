package mod.stf.syconn.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;

public enum MultiBlockAlignment implements StringRepresentable {

    TOP_LEFT(1, -1),
    MID_LEFT(1, 0),
    BOT_LEFT(1, 1),
    TOP(0, -1),
    MID(0, 0),
    BOT(0, 1),
    TOP_RIGHT(-1, -1),
    MID_RIGHT(-1, 0),
    BOT_RIGHT(-1, 1);

    private final int x;
    private final int z;

    MultiBlockAlignment(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public String getSerializedName() {
        return toString().toLowerCase();
    }

    public static boolean sameMid(MultiBlockAlignment align1, BlockPos pos1, MultiBlockAlignment align2, BlockPos pos2) {
        return pos1.offset(align1.x, 0, align1.z).equals(pos2.offset(align2.x, 0, align2.z));
    }

    public static MultiBlockAlignment fromAlignment(int x, int z) {
        for (MultiBlockAlignment alignment : MultiBlockAlignment.values()) {
            if (alignment.x == -x && alignment.z == -z) {
                return alignment;
            }
        }
        return MID;
    }
}
