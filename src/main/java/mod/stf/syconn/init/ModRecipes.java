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

    public static final List<Recipe> HILT_RECIPES = new HiltRecipeType().createRecipes();
}
