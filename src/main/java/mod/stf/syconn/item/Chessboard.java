package mod.stf.syconn.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Chessboard extends Item {

    static public int MAXIMUM_NUMBER_OF_COUNTERS = 64;
    public Chessboard()
    {
        super(new Item.Properties().stacksTo(MAXIMUM_NUMBER_OF_COUNTERS).tab(CreativeModeTab.TAB_MISC));
    }
}
