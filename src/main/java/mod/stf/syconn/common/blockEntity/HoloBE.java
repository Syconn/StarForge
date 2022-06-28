package mod.stf.syconn.common.blockEntity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HoloBE extends ClientBlockEntity {

    private boolean slim;
    private NativeImage skin;
    private String urlOrName = "Username";
    private String mode = "Username";

    public HoloBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.HOLO_BE.get(), pWorldPosition, pBlockState);
    }

    public void tickServer(Level level) {

    }

    public void setSkin(NativeImage skin) {
        this.skin = skin;
        update(worldPosition, getBlockState());
    }

    public NativeImage getSkin() {
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

    @Override
    protected CompoundTag saveData() {
        CompoundTag pTag = new CompoundTag();
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        if (skin != null)
            pTag.put("skin", NbtUtil.writeNativeImage(skin));
        return pTag;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("mode", mode);
        pTag.putString("urlorname", urlOrName);
        pTag.putBoolean("slim", slim);
        if (skin != null)
            pTag.put("skin", NbtUtil.writeNativeImage(skin));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        mode = pTag.getString("mode");
        urlOrName = pTag.getString("urlorname");
        slim = pTag.getBoolean("slim");
        if (pTag.contains("skin"))
            skin = NbtUtil.readNativeImage(pTag.getCompound("skin"));
    }
}
