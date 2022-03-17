package mod.stf.syconn.init;

import com.google.common.collect.ImmutableMap;
import mod.stf.syconn.util.recipe.Recipe;
import mod.stf.syconn.util.recipe.types.HiltRecipeType;
import mod.stf.syconn.util.recipe.types.RecipeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModRecipes {

    //public static final List<RecipeType<?>> RECIPE_TYPES = new ArrayList<>();
    public static final Map<RecipeType<?>, List<Recipe>> RECIPE_TYPES = createRecipes();
    public static final HiltRecipeType HILT_RECIPE_TYPE = new HiltRecipeType();

    public static Map<RecipeType<?>, List<Recipe>> createRecipes(){
        Map<RecipeType<?>, List<Recipe>> recipes = new HashMap<>();
        recipes.put(HILT_RECIPE_TYPE, new HiltRecipeType().getRecipes());
        return recipes;
    }
}
