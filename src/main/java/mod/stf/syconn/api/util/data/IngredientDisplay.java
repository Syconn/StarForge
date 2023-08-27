package mod.stf.syconn.api.util.data;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class IngredientDisplay {

    private final List<ItemStack> displayStacks;
    private int selectedStack = 0;
    private Ingredient ingredient;
    private long lastTime = System.currentTimeMillis();
    private ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public IngredientDisplay(Ingredient ingredient) {
        displayStacks = List.of(ingredient.getItems());
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void tick() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - this.lastTime >= 1000) {
            this.selectedStack = (this.selectedStack + 1) % this.displayStacks.size();
            this.lastTime = currentTime;
        }
    }

    public void display(PoseStack poseStack, int count, int x, int y) {
        itemRenderer.renderAndDecorateItem(poseStack, displayStacks.get(selectedStack), x, y);
        if (count > 0) itemRenderer.renderGuiItemDecorations(poseStack, Minecraft.getInstance().font, new ItemStack(displayStacks.get(selectedStack).getItem(), count), x, y);
    }
}
