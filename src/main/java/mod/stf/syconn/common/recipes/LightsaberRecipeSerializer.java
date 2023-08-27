package mod.stf.syconn.common.recipes;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class LightsaberRecipeSerializer implements RecipeSerializer<LightsaberRecipe> {

    public LightsaberRecipe fromJson(ResourceLocation pRecipeId, JsonObject parent) {
        ImmutableList.Builder<ModIngredient> builder = ImmutableList.builder();
        JsonArray input = GsonHelper.getAsJsonArray(parent, "materials");
        for(int i = 0; i < input.size(); i++) {
            JsonObject object = input.get(i).getAsJsonObject();
            builder.add(ModIngredient.fromJson(object));
        }
        if(!parent.has("result")) {
            throw new JsonSyntaxException("Missing result item entry");
        }
        JsonObject resultObject = GsonHelper.getAsJsonObject(parent, "result");
        ItemStack resultItem = ShapedRecipe.itemStackFromJson(resultObject);
        return new LightsaberRecipe(pRecipeId, resultItem, builder.build());
    }

    public LightsaberRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        ItemStack result = buffer.readItem();
        ImmutableList.Builder<ModIngredient> builder = ImmutableList.builder();
        int size = buffer.readVarInt();
        for(int i = 0; i < size; i++)
        {
            builder.add((ModIngredient) Ingredient.fromNetwork(buffer));
        }
        return new LightsaberRecipe(recipeId, result, builder.build());
    }

    public void toNetwork(FriendlyByteBuf buffer, LightsaberRecipe recipe) {
        buffer.writeItem(recipe.item());
        buffer.writeVarInt(recipe.materials().size());
        for(ModIngredient ingredient : recipe.materials()) {
            ingredient.toNetwork(buffer);
        }
    }
}
