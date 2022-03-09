package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.api.containers.slots.SpecificSlotHandler;
import mod.stf.syconn.api.containers.slots.ToggleSlotHandler;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class HiltContainer extends ContainerMenu {

    public HiltContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.HILT_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.LIGHTSABER_CRAFTER.get());
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new ToggleSlotHandler(h, 0, 12, 77));
                addSlot(new ToggleSlotHandler(h, 1, 39, 77));
                addSlot(new ToggleSlotHandler(h, 2, 66, 77));
                addSlot(new ToggleSlotHandler(h, 3, 93, 77));
                addSlot(new ToggleSlotHandler(h, 4, 120, 77));
                addSlot(new ToggleSlotHandler(h, 5, 147, 77));
            });
        }
        layoutPlayerInventorySlots(8, 102);
    }
}
