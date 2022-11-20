package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSetShip implements IMessage<MessageSetShip> {

    private Schematic ship;
    private BlockPos pos;
    private Direction direction;

    public MessageSetShip() {
    }

    public MessageSetShip(Schematic ship, BlockPos pos, Direction d) {
        this.ship = ship;
        this.pos = pos;
        this.direction = d;
    }

    @Override
    public void encode(MessageSetShip message, FriendlyByteBuf buffer) {
        buffer.writeNbt(message.ship.saveSchematic());
        buffer.writeBlockPos(message.pos);
        buffer.writeInt(message.direction.get3DDataValue());
    }

    @Override
    public MessageSetShip decode(FriendlyByteBuf buffer) {
        return new MessageSetShip(Schematic.readSchematic(buffer.readNbt()), buffer.readBlockPos(), Direction.from3DDataValue(buffer.readInt()));
    }

    @Override
    public void handle(MessageSetShip message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null){
                if (player.level.getBlockEntity(message.pos) instanceof NavBE be){
                    be.setShip(message.ship, message.direction);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
