package mod.stf.syconn.init;

import mod.stf.syconn.util.recipe.types.HiltRecipeType;
import mod.stf.syconn.util.recipe.types.RecipeType;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {

    public static final List<RecipeType> RECIPE_TYPES = new ArrayList<>();

    public static void init(){
        RECIPE_TYPES.add(new HiltRecipeType().createRecipes());
    }
}
