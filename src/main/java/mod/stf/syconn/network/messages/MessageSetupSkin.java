package mod.stf.syconn.network.messages;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.world.data.SkinManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSetupSkin implements IMessage<MessageSetupSkin> {

    private String urlOrName;
    private BlockPos pos;
    private boolean slim;

    public MessageSetupSkin() {
    }

    public MessageSetupSkin(String urlOrName, BlockPos pos, boolean slim) {
        this.urlOrName = urlOrName;
        this.pos = pos;
        this.slim = slim;
    }

    @Override
    public void encode(MessageSetupSkin message, FriendlyByteBuf buffer) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("urlorname", message.urlOrName);
        buffer.writeNbt(nbt);
        buffer.writeBlockPos(message.pos);
        buffer.writeBoolean(message.slim);
    }

    @Override
    public MessageSetupSkin decode(FriendlyByteBuf buffer) {
        CompoundTag nbt = buffer.readNbt();
        return new MessageSetupSkin(nbt.getString("urlorname"), buffer.readBlockPos(), buffer.readBoolean());
    }

    @Override
    public void handle(MessageSetupSkin message, Supplier<NetworkEvent.Context> supplier) {
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
