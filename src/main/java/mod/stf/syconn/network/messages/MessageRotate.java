package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageRotate implements IMessage<MessageRotate> {

    private Direction dRot;
    private BlockPos pos;

    public MessageRotate() {}

    public MessageRotate(Direction dRot, BlockPos pos) {
        this.dRot = dRot;
        this.pos = pos;
    }

    @Override
    public void encode(MessageRotate message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.dRot.get3DDataValue());
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageRotate decode(FriendlyByteBuf buffer) {
        return new MessageRotate(Direction.from3DDataValue(buffer.readInt()), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageRotate message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null){
                if (player.level.getBlockEntity(message.pos) instanceof NavBE be){
                    for (BlockID id : be.getShip().getBlockIDs()){
                        int x = id.pos().getX() - message.pos.getX();
                        BlockPos pos = new BlockPos(message.pos.getX() - x - 1, id.pos().getY(), id.pos().getZ());
                        player.level.setBlock(id.pos(), Blocks.AIR.defaultBlockState(), 2);
                        player.level.setBlock(pos, id.state(), 2);
                    }
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
