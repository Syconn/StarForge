package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class NavBE extends ApplicationBE<NavContainer> {

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, null);
    }

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState, BasicApplication<NavContainer> application) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, application);
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Nav Computer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new NavContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }
}
