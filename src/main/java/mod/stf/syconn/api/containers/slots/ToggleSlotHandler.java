package mod.stf.syconn.api.containers.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ToggleSlotHandler extends SlotItemHandler {

    private boolean active = true;

    public ToggleSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
