package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
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
    }
}
