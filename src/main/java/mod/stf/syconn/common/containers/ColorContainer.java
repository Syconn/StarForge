package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.api.containers.slots.SpecificSlotHandler;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.CapabilityItemHandler;

public class ColorContainer extends ContainerMenu {

    public ColorContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.COLOR_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.LIGHTSABER_CRAFTER.get());
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SpecificSlotHandler(h, 0, 8, 8, ModItems.LIGHTSABER.get()));
            });
        }
        layoutPlayerInventorySlots(8, 100);
    }
}