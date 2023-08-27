package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import mod.stf.syconn.common.recipes.LightsaberRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<LightsaberRecipeSerializer> CRAFTER = REGISTER.register("lightsaber", LightsaberRecipeSerializer::new);
}
