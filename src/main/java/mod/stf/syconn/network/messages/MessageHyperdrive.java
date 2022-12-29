package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.block.Computer;
import mod.stf.syconn.block.NavigationalComputer;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.common.entity.MovingBlock;
import mod.stf.syconn.common.entity.MovingBlockEntity;
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
    private double speed;
    private Schematic schem;

    public MessageHyperdrive() {
    }

    public MessageHyperdrive(Direction direction, int distance, double speed, Schematic schem) {
        this.direction = direction;
        this.distance = distance;
        this.speed = speed;
        this.schem = schem;
    }

    @Override
    public void encode(MessageHyperdrive message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.direction.get3DDataValue());
        buffer.writeInt(message.distance);
        buffer.writeDouble(message.speed);
        buffer.writeNbt(message.schem.saveSchematic());
    }

    @Override
    public MessageHyperdrive decode(FriendlyByteBuf buffer) {
        return new MessageHyperdrive(Direction.from3DDataValue(buffer.readInt()), buffer.readInt(), buffer.readDouble(), Schematic.readSchematic(buffer.readNbt()));
    }

    @Override
    public void handle(MessageHyperdrive message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {

            //TODO 0 is instant movement AND UPDATE NEW BLOCKPOS OF NAV COMPUTER
            ServerPlayer player = supplier.get().getSender();
            if (player != null) {
//                ServerLevel world = player.getLevel();
//                for (BlockID id : message.schem.getBlockIDs()){
//                    if (id.state().getBlock() instanceof Computer){
//                        MovingBlockEntity block = new MovingBlockEntity(world, id.state(), id.pos(), message.distance, message.direction, message.speed, (NavBE) player.level.getBlockEntity(id.pos()));
//                        world.addFreshEntity(block);
//                        world.removeBlock(id.pos(), true);
//                    } else {
//                        MovingBlock block = new MovingBlock(world, id.state(), id.pos(), message.distance, message.direction, message.speed);
//                        world.addFreshEntity(block);
//                        world.removeBlock(id.pos(), true);
//                    }
//                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
