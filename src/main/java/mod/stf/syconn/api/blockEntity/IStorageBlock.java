package mod.stf.syconn.api.blockEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public interface IStorageBlock extends Container, MenuProvider {
    ItemStackHandler getInventory();

    @Override
    default int getContainerSize() {
        return this.getInventory().getSlots();
    }

    @Override
    default boolean isEmpty() {
        for(int i = 0; i < getContainerSize(); i++) {
            if(!getInventory().getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    default ItemStack getItem(int index) {
        return index >= 0 && index < getContainerSize() ? this.getInventory().getStackInSlot(index) : ItemStack.EMPTY;
    }

    @Override
    default ItemStack removeItem(int index, int count) {
        ItemStack stack = getInventory().extractItem(index, count, false);
        if (!stack.isEmpty()) {
            this.setChanged();
        }
        return stack;
    }

    @Override
    default ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = this.getInventory().getStackInSlot(index);
        if (stack.isEmpty()) return ItemStack.EMPTY;
        else {
            this.getInventory().setStackInSlot(index, ItemStack.EMPTY);
            return stack;
        }
    }

    @Override
    default void setItem(int index, ItemStack stack) {
        this.getInventory().setStackInSlot(index, stack);
        if(!stack.isEmpty() && stack.getCount() > this.getMaxStackSize())
        {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    @Override
    default int getMaxStackSize()
    {
        return 64;
    }

    @Override
    default boolean stillValid(Player player)
    {
        return false;
    }

    @Override
    default void startOpen(Player player) {}

    @Override
    default void stopOpen(Player player) {}

    @Override
    default boolean canPlaceItem(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    default void clearContent() {
        for(int i = 0; i < getContainerSize(); i++) {
            getInventory().setStackInSlot(i, ItemStack.EMPTY);
        }
        this.setChanged();
    }
}
