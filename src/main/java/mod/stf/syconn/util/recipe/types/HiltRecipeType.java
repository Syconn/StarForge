package mod.stf.syconn.util.recipe.types;

import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class HiltRecipeType extends RecipeType<Recipe> {

    public HiltRecipeType() {
        createRecipes();
    }

    @Override
    public RecipeType<Recipe> createRecipes() {
        RECIPES.add(new Recipe(createHandle(LightsaberData.HandleType.ANAKIN), ingredient(Items.IRON_INGOT, 24), ingredient(Items.REDSTONE, 12), ingredient(Items.COAL, 22)));
        RECIPES.add(new Recipe(createHandle(LightsaberData.HandleType.AHSOKA), ingredient(Items.IRON_INGOT, 30), ingredient(Items.REDSTONE, 12), ingredient(Items.COAL, 26)));
        RECIPES.add(new Recipe(createHandle(LightsaberData.HandleType.MACE), ingredient(Items.GOLD_INGOT, 20), ingredient(Items.REDSTONE, 23), ingredient(Items.COAL, 36)));
        RECIPES.add(new Recipe(createHandle(LightsaberData.HandleType.KYLO), ingredient(Items.IRON_INGOT, 28), ingredient(Items.REDSTONE, 18), ingredient(Items.COAL, 42)));
        return this;
    }

    @Override
    public List<Recipe> getRecipes() {
        return RECIPES;
    }

    public ItemStack createHandle(LightsaberData.HandleType handle){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        LightsaberHelper.setData(stack, new LightsaberData(handle, false, new LColor(handle.getColor())));
        return stack;
    }
}