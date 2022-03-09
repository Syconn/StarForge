package mod.stf.syconn.util.recipe;

import net.minecraft.world.item.Item;

public record ModIngredient(Item item, int amount) {

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
