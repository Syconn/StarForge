package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.recipes.LightsaberRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Reference.MOD_ID);

    public static final RegistryObject<RecipeType<LightsaberRecipe>> LIGHTSABER_RECIPE = create("lightsabers");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> create(String name) {
        return REGISTER.register(name, () -> new RecipeType<>() {
            public String toString() { return name; }
        });
    }
}
