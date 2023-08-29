package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientMenuBlockEntity;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.common.containers.HoloContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class HoloBE extends ClientMenuBlockEntity {

    private boolean slim;
    private PixelImage skin;
    private String urlOrName = "Username";
    private String mode = "Username";

    public HoloBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.HOLO_BE.get(), pWorldPosition, pBlockState);
    }

    public void setSkin(PixelImage skin) {
        this.skin = skin;
        update(worldPosition, getBlockState());
    }

    public PixelImage getSkin() {
        return skin;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
        update(worldPosition, getBlockState());
    }

    public String getUrlOrName() {
        return urlOrName;
    }

    public void setUrlOrName(String urlOrName) {
        this.urlOrName = urlOrName;
        update(worldPosition, getBlockState());
    }

    public void setSlim(boolean slim) {
        this.slim = slim;
        update(worldPosition, getBlockState());
    }

    public boolean isSlim() {
        return slim;
    }

    protected CompoundTag saveData() {
        CompoundTag pTag = new CompoundTag();
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        pTag.put("items", itemHandler.serializeNBT());
        if (skin != null)
            pTag.put("skin", skin.write());
        return pTag;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        if (skin != null)
            pTag.put("skin", skin.write());
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        mode = pTag.getString("mode");
        urlOrName = pTag.getString("urlorname");
        slim = pTag.getBoolean("slim");
        if (pTag.contains("skin"))
            skin = PixelImage.read(pTag.getCompound("skin"));
    }

    public ItemStackHandler getInventory() {
        return itemHandler;
    }

    public void tickServer() {}

    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            protected void onContentsChanged(int slot) { setChanged(); }
        };
    }

    public Component getDisplayName() {
        return Component.literal("Holo Projector");
    }

    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new HoloContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }
}
