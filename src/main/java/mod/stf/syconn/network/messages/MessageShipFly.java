package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.util.ShipBody;
import mod.stf.syconn.util.FlightController;
import mod.stf.syconn.util.TripPath;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageShipFly implements IMessage<MessageShipFly> {

    private BlockPos dest;
    private int speed;
    private BlockPos block;

    public MessageShipFly() {}

    public MessageShipFly(BlockPos dest, int speed, BlockPos block) {
        this.dest = dest;
        this.speed = speed;
        this.block = block;
    }

    @Override
    public void encode(MessageShipFly message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.dest);
        buffer.writeInt(message.speed);
        buffer.writeBlockPos(message.block);
    }

    @Override
    public MessageShipFly decode(FriendlyByteBuf buffer) {
        return new MessageShipFly(buffer.readBlockPos(), buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageShipFly message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null && player.level.getBlockEntity(message.block) instanceof NavBE be){
                be.fly(message.dest, message.speed);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
