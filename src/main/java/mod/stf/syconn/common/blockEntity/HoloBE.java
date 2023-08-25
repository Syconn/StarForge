package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HoloBE extends ClientBlockEntity {

    private boolean slim;
    private PixelImage skin;
    private String urlOrName = "Username";
    private String mode = "Username";
    private ItemStack mainHand = null;
    private ItemStack offHand = null;
    private ItemStack[] armour = {null, null, null, null}; //Head - 3, ..., ..., ...

    public HoloBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.HOLO_BE.get(), pWorldPosition, pBlockState);
    }

    public void tickServer(Level level) {

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

    public void setArmour(ItemStack[] armour) {
        this.armour = armour;
        update(worldPosition, getBlockState());
    }

    public void setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
        update(worldPosition, getBlockState());
    }

    public void setOffHand(ItemStack offHand) {
        this.offHand = offHand;
        update(worldPosition, getBlockState());
    }

    public ItemStack getMainHand() {
        return mainHand;
    }

    public ItemStack getOffHand() {
        return offHand;
    }

    public ItemStack[] getArmour() {
        return armour;
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag pTag = new CompoundTag();
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        if (skin != null)
            pTag.put("skin", skin.write());
        if (mainHand != null)
            pTag.put("mainhand", mainHand.save(new CompoundTag()));
        if (offHand != null)
            pTag.put("offhand", offHand.save(new CompoundTag()));
        for (int i = 0; i < 4; i++){
            if (armour[i] != null)
                pTag.put("armour" + i, armour[i].save(new CompoundTag()));
        }
        return pTag;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        if (skin != null)
            pTag.put("skin", skin.write());
        if (mainHand != null)
            pTag.put("mainhand", mainHand.save(new CompoundTag()));
        if (offHand != null)
            pTag.put("offhand", offHand.save(new CompoundTag()));
        for (int i = 0; i < 4; i++){
            if (armour[i] != null)
                pTag.put("armour" + i, armour[i].save(new CompoundTag()));
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        mode = pTag.getString("mode");
        urlOrName = pTag.getString("urlorname");
        slim = pTag.getBoolean("slim");
        if (pTag.contains("skin"))
            skin = PixelImage.read(pTag.getCompound("skin"));
        if (pTag.contains("mainhand"))
            mainHand = ItemStack.of(pTag.getCompound("mainhand"));
        if (pTag.contains("offhand"))
            offHand = ItemStack.of(pTag.getCompound("offhand"));
        for (int i = 0; i < 4; i++){
            if (pTag.contains("armour" + i))
                armour[i] = ItemStack.of(pTag.getCompound("armour" + i));
        }
    }
}
