package mod.stf.syconn.api.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Tab {

    private Item icon;
    private int id;

    public Tab(int id, Item icon) {
        this.icon = icon;
        this.id = id;
    }

    public Item getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public TabEnum convert(){
        return TabEnum.getById(id);
    }
}