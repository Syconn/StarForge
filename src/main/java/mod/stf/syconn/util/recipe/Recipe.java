package mod.stf.syconn.util.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class Recipe {

    private ModIngredient[] inputs;
    private ItemStack output;

    public Recipe(ItemStack output, ModIngredient... inputs) {
        this.inputs = inputs;
        this.output = output;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ModIngredient[] getInputs() {
        return inputs;
    }
}
