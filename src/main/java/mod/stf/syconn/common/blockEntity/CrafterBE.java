package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.MenuBlockEntity;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class CrafterBE extends MenuBlockEntity {

    public CrafterBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRAFTER_BE.get(), pos, state);
    }

    public void tickServer() {}

    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(7) { protected void onContentsChanged(int slot) {
                setChanged();
            } };
    }

    public Component getDisplayName() {
        return Component.empty();
    }

    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
        if (getBlockState().getValue(LightsaberCrafter.MODE) == LightsaberCrafter.States.COLOR){
            return new ColorContainer(windowId, worldPosition, playerInventory, playerEntity);
        }
        if (getBlockState().getValue(LightsaberCrafter.MODE) == LightsaberCrafter.States.HILT){
            return new HiltContainer(windowId, worldPosition, playerInventory, playerEntity);
        }
        return new ColorContainer(windowId, worldPosition, playerInventory, playerEntity);
    }

    public ItemStackHandler getInventory() {
        return itemHandler;
    }
}
