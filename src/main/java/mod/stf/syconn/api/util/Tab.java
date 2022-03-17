package mod.stf.syconn.api.util;

import net.minecraft.world.item.Item;

public record Tab(int id, Item icon, String name) {

    public TabEnum convert() {
        return TabEnum.getById(id);
    }
}