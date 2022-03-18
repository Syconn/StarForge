package mod.stf.syconn.util.recipe.types;

import mod.stf.syconn.util.recipe.ModIngredient;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class RecipeType<T extends Recipe> {
    public ModIngredient ingredient(Item item, int count){
        return new ModIngredient(item, count);
    }
    abstract List<T> createRecipes();
}
