package mod.stf.syconn.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.recipes.LightsaberRecipe;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.init.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

@JeiPlugin
public class StarForgeJEI implements IModPlugin {

    public static final RecipeType<LightsaberRecipe> LIGHTSABER = RecipeType.create(Reference.MOD_ID, "lightsaber", LightsaberRecipe.class);

    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MOD_ID, "crafting");
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(ModItems.LIGHTSABER.get());
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new LightsaberCategory(helper));
    }

    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel world = Objects.requireNonNull(Minecraft.getInstance().level);
        registration.addRecipes(LIGHTSABER, world.getRecipeManager().getAllRecipesFor(ModRecipes.LIGHTSABER_RECIPE.get()));
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.LIGHTSABER_CRAFTER.get()), LIGHTSABER);
    }
}
