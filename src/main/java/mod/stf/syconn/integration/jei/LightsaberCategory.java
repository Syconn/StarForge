package mod.stf.syconn.integration.jei;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.recipes.LightsaberRecipe;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LightsaberCategory implements IRecipeCategory<LightsaberRecipe> {

    private static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "lightsaber");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Reference.MOD_ID, "textures/gui/jei_workbench.png");
    private static final String TITLE_KEY = Reference.MOD_ID + ".category.lightsaber.title";
    private static final String MATERIALS_KEY = Reference.MOD_ID + ".category.lightsaber.materials";

    private final IDrawableStatic background;
    private final IDrawableStatic window;
    private final IDrawableStatic inventory;
    private final IDrawable icon;
    private final Component title;

    public LightsaberCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(162, 124);
        this.window = helper.createDrawable(BACKGROUND, 7, 15, 162, 72);
        this.inventory = helper.createDrawable(BACKGROUND, 7, 101, 162, 36);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.LIGHTSABER_CRAFTER.get()));
        this.title = Component.translatable(TITLE_KEY);
    }

    public RecipeType<LightsaberRecipe> getRecipeType() {
        return StarForgeJEI.LIGHTSABER;
    }

    public Component getTitle() {
        return this.title;
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, LightsaberRecipe recipe, IFocusGroup focuses) {
        ItemStack output = recipe.item();
        for(int i = 0; i < recipe.materials().size(); i++) {
            List<ItemStack> stacks = new ArrayList<>();
            for (ItemStack mat : recipe.materials().get(i).getItems()) stacks.add(new ItemStack(mat.getItem(), recipe.materials().get(i).getCount()));
            builder.addSlot(RecipeIngredientRole.INPUT, (i % 8) * 18 + 1, 88 + (i / 8) * 18).addItemStacks(stacks);
        }
        builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(output);
    }

    public void draw(LightsaberRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.window.draw(poseStack, 0, 0);
        this.inventory.draw(poseStack, 0, this.window.getHeight() + 2 + 11 + 2);

        Minecraft.getInstance().font.draw(poseStack, I18n.get(MATERIALS_KEY), 0, 78, 0x7E7E7E);
        ItemStack output = recipe.item();
        int titleX = this.window.getWidth() / 2;
        GuiComponent.drawCenteredString(poseStack, Minecraft.getInstance().font, LightsaberHelper.getData(output).getHandle().getName() + " " + output.getHoverName().getString(), titleX, 5, 0xFFFFFFFF);
        PoseStack stack = RenderSystem.getModelViewStack();
        stack.pushPose();
        {
            stack.mulPoseMatrix(poseStack.last().pose());
            stack.translate(81, 40, 0);
            stack.scale(40F, 40F, 40F);
            stack.mulPose(Axis.XP.rotationDegrees(-5F));
            float partialTicks = Minecraft.getInstance().getFrameTime();
            stack.mulPose(Axis.YP.rotationDegrees(Minecraft.getInstance().player.tickCount + partialTicks));
            stack.scale(-1, -1, -1);
            RenderSystem.applyModelViewMatrix();

            BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(output, null, Minecraft.getInstance().player, 0);
            Lighting.setupFor3DItems();
            MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
            Minecraft.getInstance().getItemRenderer().render(output, ItemDisplayContext.FIXED, false, new PoseStack(), buffer, 15728880, OverlayTexture.NO_OVERLAY, model);
            buffer.endBatch();
        }
        stack.popPose();
        RenderSystem.applyModelViewMatrix();
    }
}
