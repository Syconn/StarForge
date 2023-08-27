package mod.stf.syconn.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModRecipeSerializers;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class LightsaberRecipeBuilder {

    private final RecipeCategory category;
    private final ItemStack result;
    private final int count;
    private final List<ModIngredient> ingredients;
    private final Advancement.Builder advancementBuilder;
    private final List<ICondition> conditions = new ArrayList<>();

    private LightsaberRecipeBuilder(@Nullable RecipeCategory category, ItemStack item, int count) {
        this.category = category;
        this.result = item;
        this.count = count;
        this.ingredients = new ArrayList<>();
        this.advancementBuilder = Advancement.Builder.advancement();
    }

    public static LightsaberRecipeBuilder crafting(ItemStack item) {
        return new LightsaberRecipeBuilder(RecipeCategory.COMBAT, item, 1);
    }

    public LightsaberRecipeBuilder addIngredient(ItemLike item, int count) {
        this.ingredients.add(ModIngredient.of(item, count));
        return this;
    }

    public LightsaberRecipeBuilder addIngredient(TagKey<Item> item, int count) {
        this.ingredients.add(ModIngredient.of(item, count));
        return this;
    }

    public LightsaberRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        ResourceLocation resourcelocation = new ResourceLocation(Reference.MOD_ID, LightsaberHelper.getData(result).getHandle().getType());
        this.build(consumer, resourcelocation);
    }

    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new LightsaberRecipeBuilder.Result(id, this.result, this.count, this.ingredients, this.conditions, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + (this.category != null ? this.category.getFolderName() : "") + "/" + id.getPath())));
    }

    /**
     * Makes sure that this recipe is valid and obtainable.
     */
    private void validate(ResourceLocation id) {
        if(this.advancementBuilder.getCriteria().isEmpty())
        {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final ItemStack item;
        private final int count;
        private final List<ModIngredient> ingredients;
        private final List<ICondition> conditions;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, ItemStack item, int count, List<ModIngredient> ingredients, List<ICondition> conditions, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.item = item;
            this.count = count;
            this.ingredients = ingredients;
            this.conditions = conditions;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(JsonObject json)
        {
            JsonArray conditions = new JsonArray();
            this.conditions.forEach(condition -> conditions.add(CraftingHelper.serialize(condition)));
            if(conditions.size() > 0)
            {
                json.add("conditions", conditions);
            }

            JsonArray materials = new JsonArray();
            this.ingredients.forEach(ingredient -> materials.add(ingredient.toJson()));
            json.add("materials", materials);

            JsonObject resultObject = new JsonObject();
            resultObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.item.getItem())).toString());
            resultObject.addProperty("nbt", item.getTag().getAsString());
            if(this.count > 1)
            {
                resultObject.addProperty("count", this.count);
            }
            json.add("result", resultObject);
        }

        public ResourceLocation getId()
        {
            return this.id;
        }

        public RecipeSerializer<?> getType()
        {
            return ModRecipeSerializers.CRAFTER.get();
        }

        public JsonObject serializeAdvancement()
        {
            return this.advancement.serializeToJson();
        }

        public ResourceLocation getAdvancementId()
        {
            return this.advancementId;
        }
    }
}
