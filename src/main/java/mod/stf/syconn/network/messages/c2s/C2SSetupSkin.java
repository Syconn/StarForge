package mod.stf.syconn.network.messages.c2s;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.messages.IMessage;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetupSkin implements IMessage<C2SSetupSkin> {

    private String urlOrName;
    private BlockPos pos;
    private boolean slim;

    public C2SSetupSkin() {
    }

    public C2SSetupSkin(String urlOrName, BlockPos pos, boolean slim) {
        this.urlOrName = urlOrName;
        this.pos = pos;
        this.slim = slim;
    }

    @Override
    public void encode(C2SSetupSkin message, FriendlyByteBuf buffer) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("urlorname", message.urlOrName);
        buffer.writeNbt(nbt);
        buffer.writeBlockPos(message.pos);
        buffer.writeBoolean(message.slim);
    }

    @Override
    public C2SSetupSkin decode(FriendlyByteBuf buffer) {
        CompoundTag nbt = buffer.readNbt();
        return new C2SSetupSkin(nbt.getString("urlorname"), buffer.readBlockPos(), buffer.readBoolean());
    }

    @Override
    public void handle(C2SSetupSkin message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null && player.level.getBlockEntity(message.pos) instanceof HoloBE) {
                HoloBE be = (HoloBE) player.level.getBlockEntity(message.pos);
                be.setUrlOrName(message.urlOrName);

                player.level.getCapability(SkinManager.SKINS).ifPresent(cap -> {
                    if (cap.contains(message.urlOrName)) {
                        be.setSkin(cap.getSkin(message.urlOrName));
                        be.setSlim(cap.getSlimSkin(message.urlOrName));
                    } else {
                        NativeImage skin;
                        boolean slim;
                        if (be.getMode().matches("URL")){
                            skin = SkinGrabber.getSkinTexture(message.urlOrName);
                            slim = message.slim;
                        } else {
                            skin = SkinGrabber.getSkinTexture(SkinGrabber.getTextureURL(message.urlOrName));
                            slim = SkinGrabber.getModelType(message.urlOrName);
                        }
                        be.setSkin(new PixelImage(skin));
                        be.setSlim(slim);
                        cap.putSkins(message.urlOrName, new PixelImage(skin));
                        cap.putSlimSkins(message.urlOrName, slim);
                    }
                });
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
