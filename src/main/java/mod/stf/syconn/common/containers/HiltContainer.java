package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.common.recipes.LightsaberRecipe;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import java.util.List;

public class HiltContainer extends ContainerMenu {

    public int recipeNum = 0;
    public List<LightsaberRecipe> recipes;
    public LightsaberRecipe selectedRecipe;

    public HiltContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.HILT_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.LIGHTSABER_CRAFTER.get());
        recipes = player.level.getRecipeManager().getAllRecipesFor(ModRecipes.LIGHTSABER_RECIPE.get());
        selectedRecipe = recipes.get(recipeNum);
        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 176, 31));
            });
        }
        layoutPlayerInventorySlots(8, 102);
    }
}
