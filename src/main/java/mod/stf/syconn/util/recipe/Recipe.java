package mod.stf.syconn.util.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class Recipe {

    private ModIngredient[] inputs;
    private ItemStack output;
    private int id;

    public Recipe(ItemStack output, int id, ModIngredient... inputs) {
        this.inputs = inputs;
        this.output = output;
        this.id = id;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ModIngredient[] getInputs() {
        return inputs;
    }

    public int getId() {
        return id;
    }
}
