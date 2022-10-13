package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ApplicationContainer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class NavContainer extends ApplicationContainer {

    public NavContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.NAV_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.NAV_COMPUTER.get());
    }
}
