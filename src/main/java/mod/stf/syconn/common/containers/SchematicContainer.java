package mod.stf.syconn.common.containers;

import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.api.containers.slots.SpecificSlotHandler;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageLoadBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class SchematicContainer extends ContainerMenu {

    public SchematicContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.SCHEM_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.SCHEMATIC_PROJECTOR.get());
        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                addSlot(new SpecificSlotHandler(h, 0, 80, 32, ModItems.SCHEMATIC_ITEM.get()));
            });
        }
        layoutPlayerInventorySlots(8, 87);
    }

    @Override
    public void slotsChanged(Container pContainer) {
        Network.getPlayChannel().sendToServer(new MessageLoadBlock(blockEntity.getBlockPos()));
        super.slotsChanged(pContainer);
    }
}
