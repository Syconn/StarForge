package mod.stf.syconn.util.recipe.types;

import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class HiltRecipeType extends RecipeType<Recipe> {

    @Override
    public List<Recipe> createRecipes() {
        //TODO REPLACE NETHERITE WITH CUSTOM INGOTS LIKE BESCAR

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.ANAKIN), 0, ingredient(Items.IRON_INGOT, 24), ingredient(Items.REDSTONE, 12), ingredient(Items.COAL, 22)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.AHSOKA), recipes.size(), ingredient(Items.IRON_INGOT, 30), ingredient(Items.REDSTONE, 12), ingredient(Items.COAL, 26)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.DARK_SABER), recipes.size(), ingredient(Items.IRON_INGOT, 14), ingredient(Items.REDSTONE, 18), ingredient(Items.COAL, 42), ingredient(Items.NETHERITE_INGOT, 2)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.MACE), recipes.size(), ingredient(Items.GOLD_INGOT, 34), ingredient(Items.REDSTONE, 23), ingredient(Items.COAL, 36)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.KYLO), recipes.size(), ingredient(Items.IRON_INGOT, 28), ingredient(Items.REDSTONE, 18), ingredient(Items.COAL, 42)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.GUARD), recipes.size(), ingredient(Items.QUARTZ, 42), ingredient(Items.REDSTONE, 18)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.KAL), recipes.size(), ingredient(Items.COPPER_INGOT, 24), ingredient(Items.REDSTONE, 18), ingredient(Items.COAL, 52), ingredient(Items.NETHERITE_INGOT, 1)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.LUKE), recipes.size(), ingredient(Items.IRON_INGOT, 37), ingredient(Items.COPPER_INGOT, 16), ingredient(Items.REDSTONE_BLOCK, 5)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.MAUL), recipes.size(), ingredient(Items.IRON_INGOT, 52), ingredient(Items.COAL, 38), ingredient(Items.REDSTONE_BLOCK, 12)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.OBI), recipes.size(), ingredient(Items.IRON_INGOT, 12), ingredient(Items.COAL, 12), ingredient(Items.REDSTONE_LAMP, 2), ingredient(Items.NETHERITE_INGOT, 1), ingredient(Items.IRON_NUGGET, 24)));
        recipes.add(new Recipe(createHandle(LightsaberData.HandleType.YODA), recipes.size(), ingredient(Items.IRON_INGOT, 6), ingredient(Items.COAL, 4), ingredient(Items.REDSTONE_TORCH, 4)));
        return recipes;
    }

    public ItemStack createHandle(LightsaberData.HandleType handle){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        LightsaberHelper.setData(stack, new LightsaberData(handle, false, new LColor(handle.getColor())));
        return stack;
    }
}