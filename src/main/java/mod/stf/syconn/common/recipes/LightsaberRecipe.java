package mod.stf.syconn.common.recipes;

import com.google.common.collect.ImmutableList;
import mod.stf.syconn.common.blockEntity.CrafterBE;
import mod.stf.syconn.init.ModRecipeSerializers;
import mod.stf.syconn.init.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record LightsaberRecipe(ResourceLocation id, ItemStack item, ImmutableList<ModIngredient> materials) implements Recipe<CrafterBE> {

    public boolean matches(CrafterBE pContainer, Level pLevel) {
        return false;
    }

    public ItemStack assemble(CrafterBE pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return item.copy();
    }

    public ResourceLocation getId() {
        return id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTER.get();
    }

    public RecipeType<?> getType() {
        return ModRecipes.LIGHTSABER_RECIPE.get();
    }

    public static LightsaberRecipe getRecipeById(Level world, ResourceLocation id) {
        return world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == ModRecipes.LIGHTSABER_RECIPE.get())
                .map(recipe -> (LightsaberRecipe) recipe)
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst().orElse(null);
    }
}
