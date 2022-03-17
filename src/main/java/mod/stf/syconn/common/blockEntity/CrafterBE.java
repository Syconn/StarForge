package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.MenuBlockEntity;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class CrafterBE extends MenuBlockEntity {

    public CrafterBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRAFTER_BE.get(), pos, state);
    }

    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(7) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
        if (getBlockState().getValue(LightsaberCrafter.MODE) == LightsaberCrafter.States.COLOR){
            return new ColorContainer(windowId, worldPosition, playerInventory, playerEntity);
        }
        if (getBlockState().getValue(LightsaberCrafter.MODE) == LightsaberCrafter.States.HILT){
            return new HiltContainer(windowId, worldPosition, playerInventory, playerEntity);
        }
        return new ColorContainer(windowId, worldPosition, playerInventory, playerEntity);
    }
}
