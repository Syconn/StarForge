package mod.stf.syconn.api.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public abstract class ApplicationContainer extends ContainerMenu {

    public ApplicationContainer(@Nullable MenuType<?> pMenuType, int windowId, BlockPos pos, Inventory playerInventory, Player player, Block block) {
        super(pMenuType, windowId, pos, playerInventory, player, block);
    }
}
