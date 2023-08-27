package mod.stf.syconn.client.datagen;

import mod.stf.syconn.common.recipes.LightsaberRecipeBuilder;
import mod.stf.syconn.common.recipes.ModIngredient;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SCHEMATIC_ITEM.get())
                .pattern("dbd")
                .pattern("pmp")
                .pattern("dpd")
                .define('d', Items.LIGHT_BLUE_DYE)
                .define('b', Items.BLACK_DYE)
                .define('m', Items.MAP)
                .define('p', Items.PAPER)
                .unlockedBy("has_mats", inventoryTrigger(ItemPredicate.Builder.item().of(Items.MAP, Items.PAPER).build()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.F_11D.get())
                .pattern("d b")
                .pattern("iii")
                .pattern(" ri")
                .define('d', Items.RED_DYE)
                .define('b', Items.LIGHT_BLUE_DYE)
                .define('i', Items.IRON_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_mats", inventoryTrigger(ItemPredicate.Builder.item().of(Items.MAP, Items.PAPER).build()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.LIGHTSABER_CRAFTER.get())
                .pattern(" a ")
                .pattern("iii")
                .pattern("ipi")
                .define('a', Items.IRON_AXE)
                .define('i', Items.IRON_INGOT)
                .define('p', Items.IRON_PICKAXE)
                .unlockedBy("has_mats", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.HOLO_PROJECTOR.get())
                .pattern("rgr")
                .pattern("rnr")
                .pattern("rrr")
                .define('r', Items.REDSTONE)
                .define('n', Items.NETHERITE_INGOT)
                .define('g', Items.GOLD_INGOT)
                .unlockedBy("has_mats", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SCHEMATIC_PROJECTOR.get())
                .pattern("rgr")
                .pattern("rnr")
                .pattern("rrr")
                .define('r', Items.REDSTONE)
                .define('n', Items.NETHERITE_INGOT)
                .define('g', Items.DIAMOND)
                .unlockedBy("has_mats", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(consumer);

        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.ANAKIN))
                .addIngredient(Tags.Items.INGOTS_IRON, 24)
                .addIngredient(Items.COAL, 22)
                .addIngredient(Items.REDSTONE, 12)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.AHSOKA))
                .addIngredient(Tags.Items.INGOTS_IRON, 30)
                .addIngredient(Items.COAL, 26)
                .addIngredient(Items.REDSTONE, 12)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.DARK_SABER))
                .addIngredient(Tags.Items.INGOTS_IRON, 14)
                .addIngredient(Items.COAL, 42)
                .addIngredient(Items.REDSTONE, 18)
                .addIngredient(Items.NETHERITE_INGOT, 2)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.MACE))
                .addIngredient(Tags.Items.INGOTS_IRON, 14)
                .addIngredient(Items.COAL, 36)
                .addIngredient(Items.REDSTONE, 23)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.KYLO))
                .addIngredient(Tags.Items.INGOTS_IRON, 28)
                .addIngredient(Items.REDSTONE, 18)
                .addIngredient(Items.COAL, 42)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.GUARD))
                .addIngredient(Items.QUARTZ, 42)
                .addIngredient(Items.REDSTONE, 18)
                .addCriterion("has_quartz_ingot", has(Items.QUARTZ))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.KAL))
                .addIngredient(Tags.Items.INGOTS_COPPER, 28)
                .addIngredient(Items.REDSTONE, 18)
                .addIngredient(Items.COAL, 42)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_COPPER))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.LUKE))
                .addIngredient(Tags.Items.INGOTS_IRON, 37)
                .addIngredient(Tags.Items.INGOTS_COPPER, 16)
                .addIngredient(Items.REDSTONE_BLOCK, 5)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_redstone_block", has(Items.REDSTONE_BLOCK))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.MAUL))
                .addIngredient(Items.IRON_INGOT, 52)
                .addIngredient(Items.COAL, 38)
                .addIngredient(Items.REDSTONE_BLOCK, 12)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .addCriterion("has_redstone_block", has(Items.REDSTONE_BLOCK))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.OBI))
                .addIngredient(Items.IRON_INGOT, 12)
                .addIngredient(Items.COAL, 12)
                .addIngredient(Items.IRON_NUGGET, 24)
                .addIngredient(Tags.Items.INGOTS_NETHERITE, 1)
                .addIngredient(Items.REDSTONE_LAMP, 1)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .addCriterion("has_redstone_block", has(Items.REDSTONE_BLOCK))
                .build(consumer);
        LightsaberRecipeBuilder.crafting(createHandle(LightsaberData.HandleType.YODA))
                .addIngredient(Items.IRON_INGOT, 16)
                .addIngredient(Items.COAL, 12)
                .addIngredient(Items.REDSTONE_TORCH, 4)
                .addCriterion("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_coal_ingot", has(Items.COAL))
                .build(consumer);
    }

    public ItemStack createHandle(LightsaberData.HandleType handle){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        LightsaberHelper.setData(stack, new LightsaberData(handle, false, new LColor(handle.getColor())));
        return stack;
    }
}
