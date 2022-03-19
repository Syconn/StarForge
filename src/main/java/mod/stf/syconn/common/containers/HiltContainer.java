package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.api.containers.slots.SpecificSlotHandler;
import mod.stf.syconn.api.containers.slots.ToggleSlotHandler;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.init.ModRecipes;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.List;

public class HiltContainer extends ContainerMenu {

    public ToggleSlotHandler[] slotHandler = new ToggleSlotHandler[6];
    public final List<Recipe> recipes = ModRecipes.HILT_RECIPES;
    public int recipeNum = 0;
    public Recipe selectedRecipe = recipes.get(recipeNum);

    public HiltContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.HILT_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.LIGHTSABER_CRAFTER.get());
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 176, 31));
            });
        }
        layoutPlayerInventorySlots(8, 102);
    }
}
