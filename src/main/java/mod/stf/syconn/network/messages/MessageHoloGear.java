package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageHoloGear implements IMessage<MessageHoloGear> {

    private BlockPos pos;
    private String type;

    public MessageHoloGear() {}

    public MessageHoloGear(String type, BlockPos pos) {
        this.pos = pos;
        this.type = type;
    }

    @Override
    public void encode(MessageHoloGear message, FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        tag.putString("type", message.type);
        buffer.writeNbt(tag);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageHoloGear decode(FriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();
        return new MessageHoloGear(tag.getString("type"), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageHoloGear message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null) {
                HoloBE be = (HoloBE) player.level.getBlockEntity(message.pos);

                if (message.type.equals("M")){
                    be.setMainHand(player.getMainHandItem());
                } else if (message.type.equals("O")) {
                    be.setOffHand(player.getOffhandItem());
                } else if (message.type.equals("A")){
                    be.setArmour(new ItemStack[]{player.getInventory().getArmor(0), player.getInventory().getArmor(1), player.getInventory().getArmor(2), player.getInventory().getArmor(3)});
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
