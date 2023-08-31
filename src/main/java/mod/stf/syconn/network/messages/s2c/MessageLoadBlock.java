package mod.stf.syconn.network.messages.s2c;

import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageLoadBlock implements IMessage<MessageLoadBlock> {

    private BlockPos pos;

    public MessageLoadBlock() {
    }

    public MessageLoadBlock(BlockPos pos) {
        this.pos = pos;
    }

    public void encode(MessageLoadBlock message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    public MessageLoadBlock decode(FriendlyByteBuf buffer) {
        return new MessageLoadBlock(buffer.readBlockPos());
    }

    public void handle(MessageLoadBlock message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null){
                SchematicBe be = (SchematicBe) player.level.getBlockEntity(message.pos);
                be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                    be.setSchematic(Schematic.readSchematic(player.getLevel(), h.getStackInSlot(0).getOrCreateTag().getCompound("schematic")));
                    be.createBlockImage();
                });
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
