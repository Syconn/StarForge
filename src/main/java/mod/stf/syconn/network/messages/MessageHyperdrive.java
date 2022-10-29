package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.entity.MovingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.io.IOException;
import java.util.function.Supplier;

public class MessageHyperdrive implements IMessage<MessageHyperdrive> {

    private Direction direction;
    private int distance;
    private Schematic schem;

    public MessageHyperdrive() {
    }

    public MessageHyperdrive(Direction direction, int distance, Schematic schem) {
        this.direction = direction;
        this.distance = distance;
        this.schem = schem;
    }

    @Override
    public void encode(MessageHyperdrive message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.direction.get3DDataValue());
        buffer.writeInt(message.distance);
        buffer.writeNbt(message.schem.saveSchematic());
    }

    @Override
    public MessageHyperdrive decode(FriendlyByteBuf buffer) {
        return new MessageHyperdrive(Direction.from3DDataValue(buffer.readInt()), buffer.readInt(), Schematic.readSchematic(buffer.readNbt()));
    }

    @Override
    public void handle(MessageHyperdrive message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {

            //TODO 0 is instant movement
            ServerPlayer player = supplier.get().getSender();
            if (player != null) {
                ServerLevel world = player.getLevel();
                for (BlockPos pos : message.schem.getBlocks()){
                    BlockState state = world.getBlockState(pos);
                    try {
                        MovingBlock block = new MovingBlock(world, state, pos, message.distance, message.direction, 10.0);
                        world.addFreshEntity(block);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    world.removeBlock(pos, true);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
